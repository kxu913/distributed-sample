package main

import (
	"embed"
	"flag"
	"fmt"
	"html/template"
	"os"
	"strings"
)

//go:embed templates/*
var f embed.FS

var project = "uim"
var workspaceFolder = "d:/workspace/java-sample"
var rootPackage = fmt.Sprintf("com.kevin.sample.%s.", project)
var domainPackage = "com.kevin.sample.domain"
var projectFolder = fmt.Sprintf("%s/%s/src/main/java/%s", workspaceFolder, project, strings.ReplaceAll(rootPackage, ".", "/"))
var domainFolder = fmt.Sprintf("%s/domain/src/main/java/%s/", workspaceFolder, strings.ReplaceAll(domainPackage, ".", "/"))
var serviceFolder = fmt.Sprintf("%s/service/", projectFolder)
var implFolder = fmt.Sprintf("%s/service/impl/", projectFolder)
var mapperFolder = fmt.Sprintf("%s/mapper/", projectFolder)
var controllerFolder = fmt.Sprintf("%s/controller/", projectFolder)

var servicePackage = fmt.Sprintf("%sservice", rootPackage)
var implPackage = fmt.Sprintf("%sservice.impl", rootPackage)
var mapperPackage = fmt.Sprintf("%smapper", rootPackage)
var controllerPackage = fmt.Sprintf("%scontroller", rootPackage)

var (
	local  bool
	host   string
	dbname string
	port   int
	user   string
	pwd    string
	tables string
)

func main() {

	flag.StringVar(&workspaceFolder, "workspace", workspaceFolder, "workspace that store codes")
	flag.StringVar(&project, "project", project, "project name")
	flag.StringVar(&domainPackage, "domain", domainPackage, "domain package, assume that use multiple maven modules to construct project")
	flag.StringVar(&host, "host", "localhost", "数据库Host")
	flag.IntVar(&port, "port", 5432, "数据库端口")
	flag.StringVar(&dbname, "dbname", "sampleuser", "数据库")
	flag.StringVar(&user, "user", "postgres", "数据库用户名")
	flag.StringVar(&pwd, "pwd", "postgres", "数据库密码")
	flag.StringVar(&tables, "tables", "t_user", "数据表名称，如果有多个用','隔开")
	flag.Parse()

	dbConfig := DBConfig{
		Host:     host,
		Port:     port,
		DBName:   dbname,
		UserName: user,
		Password: pwd,
	}

	createOutputFolder()

	_tables := strings.Split(tables, ",")
	for _, t := range _tables {
		fmt.Printf("generate code for %s \r\n", t)
		tableInfo := GetTableInfo(&dbConfig, t)
		createMapper(tableInfo)
		createDomain(tableInfo)
		createService(tableInfo)
		createImpl(tableInfo)
		createController(tableInfo)
	}
}

func createOutputFolder() {
	_, err := os.Stat(serviceFolder)
	if os.IsNotExist(err) {
		os.MkdirAll(serviceFolder, 0755)
	}
	_, err1 := os.Stat(implFolder)
	if os.IsNotExist(err1) {
		os.MkdirAll(implFolder, 0755)
	}
	_, err2 := os.Stat(mapperFolder)
	if os.IsNotExist(err2) {
		os.MkdirAll(mapperFolder, 0755)
	}
	_, err3 := os.Stat(controllerFolder)
	if os.IsNotExist(err3) {
		os.MkdirAll(controllerFolder, 0755)
	}
}

func createMapper(tableInfo *TableInfo) {
	tpl := getTpl("mapper")
	file := WriteFile(fmt.Sprintf("%s%sMapper.java", mapperFolder, tableInfo.Domain))
	defer file.Close()
	tpl.Execute(file, map[string]any{
		"TableInfo": &tableInfo,
		"Package":   mapperPackage,
		"lt":        template.HTML("<"),
		"left":      template.HTML("#{"),
		"right":     template.HTML("}"),
	})

}

func createDomain(tableInfo *TableInfo) {
	tpl := getTpl("domain")
	file := WriteFile(fmt.Sprintf("%s%s.java", domainFolder, tableInfo.Domain))
	defer file.Close()
	tpl.Execute(file, map[string]any{
		"TableInfo": &tableInfo,
		"Package":   domainPackage,
	})

}

func createService(tableInfo *TableInfo) {
	tpl := getTpl("service")
	file := WriteFile(fmt.Sprintf("%s%sService.java", serviceFolder, tableInfo.Domain))
	defer file.Close()
	tpl.Execute(file, map[string]any{
		"TableInfo": &tableInfo,
		"Package":   servicePackage,
		"lt":        template.HTML("<"),
	})

}

func createImpl(tableInfo *TableInfo) {
	tpl := getTpl("impl")
	file := WriteFile(fmt.Sprintf("%s%sServiceImpl.java", implFolder, tableInfo.Domain))
	defer file.Close()
	tpl.Execute(file, map[string]any{
		"TableInfo":   &tableInfo,
		"Package":     implPackage,
		"Project":     project,
		"FieldDomain": Lcfirst(tableInfo.Domain),
		"lt":          template.HTML("<"),
	})
}
func createController(tableInfo *TableInfo) {
	tpl := getTpl("controller")
	file := WriteFile(fmt.Sprintf("%s%sController.java", controllerFolder, tableInfo.Domain))
	defer file.Close()
	tpl.Execute(file, map[string]any{
		"TableInfo":   &tableInfo,
		"Package":     controllerPackage,
		"Project":     project,
		"FieldDomain": Lcfirst(tableInfo.Domain),
		"lt":          template.HTML("<"),
	})
}

func getTpl(name string) *template.Template {
	tplPath := "templates/"
	if local {

		tpl, _ := template.New(name).Funcs(template.FuncMap{
			"unescapeHTML": unescapeHTML,
		}).ParseFiles(tplPath + name + ".tpl")
		return tpl
	} else {
		tpl, err := template.ParseFS(f, tplPath+name+".tpl")
		if err != nil {
			panic(err)
		}
		tpl.Funcs(template.FuncMap{
			"unescapeHTML": unescapeHTML,
		})
		return tpl
	}
}

func unescapeHTML(s string) template.HTML {
	return template.HTML(s)
}
