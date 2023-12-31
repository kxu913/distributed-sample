package main

import (
	"fmt"
	"strings"
	"time"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

const (
	DSNTPL         = "host=%s  port=%d user=%s password=%s dbname=%s sslmode=disable"
	LIST_TABLES    = "select tablename from pg_tables where schemaname='public'"
	DESCRIBE_TABLE = `
		SELECT a.attname AS Field, t.typname AS Type 
		FROM pg_class c
		INNER JOIN pg_attribute a ON a.attrelid = c.oid
		INNER JOIN pg_type t ON a.atttypid = t.oid
		LEFT JOIN pg_description b ON a.attrelid = b.objoid AND a.attnum = b.objsubid
		WHERE c.relname = '%s' AND a.attnum > 0 
		ORDER BY a.attnum
	`
)

var (
	TypeMapping = map[string]string{
		"int4":      "int",
		"numeric":   "int",
		"timestamp": "Date",
		"text":      "String",
		"int8":      "long",
		"bool":      "boolean",
	}
)

type DBConfig struct {
	Host     string `default:"localhost"`
	Port     int    `default:"5432"`
	UserName string `default:"postgres"`
	Password string `default:"postgres"`
	DBName   string `default:"sampleuser"`
}

type TableInfo struct {
	DBTable        string // the table name
	Domain         string // set as domain name
	Id             string
	IdCaml         string
	Fields         []FieldType
	ContainsTime   bool `gorm:"-"`
	ContainsArray  bool `gorm:"-"`
	LastFieldIndex int  `gorm:"-"` // used to define need add "," or not
}
type FieldType struct {
	Field     string
	FieldCaml string `gorm:"-"`
	Type      string
	JavaType  string
}

func openDB(dbconfig *DBConfig) *gorm.DB {
	db, err := gorm.Open(postgres.Open(fmt.Sprintf(DSNTPL, dbconfig.Host, dbconfig.Port, dbconfig.UserName, dbconfig.Password, dbconfig.DBName)), &gorm.Config{})
	if err != nil {
		return nil
	}

	sqlDB, _ := db.DB()

	sqlDB.SetMaxIdleConns(10)

	sqlDB.SetMaxOpenConns(50)

	sqlDB.SetConnMaxLifetime(time.Minute)
	return db
}

func TableList(dbconfig *DBConfig) []string {

	g := openDB(dbconfig)
	defer Close(g)

	rows, err := g.Raw(LIST_TABLES).Rows()
	if err != nil {
		panic(err)
	}

	defer rows.Close()
	tables := []string{}
	for rows.Next() {
		var table string
		rows.Scan(&table)
		tables = append(tables, table)
	}
	return tables

}

func GetTableInfo(dbconfig *DBConfig, tableName string) *TableInfo {

	g := openDB(dbconfig)
	defer Close(g)

	rows, err := g.Raw(fmt.Sprintf(DESCRIBE_TABLE, tableName)).Rows()
	if err != nil {
		panic(err)
	}

	defer rows.Close()
	infos := []FieldType{}
	var containsTime = false
	var containsIntArray = false
	var containsStringArray = false
	var id string
	for rows.Next() {
		var info = FieldType{}
		g.ScanRows(rows, &info)
		t := info.Type
		javaType, _ := TypeMapping[t]
		if !containsTime {
			containsTime = info.Type == "timestamp"
		}
		if !containsStringArray {
			containsStringArray = info.Type == "_text"
		}
		if !containsIntArray {
			containsIntArray = info.Type == "_numeric"
		}
		if strings.Contains(info.Field, "_id") || strings.EqualFold(info.Field, "id") {
			id = info.Field
		}
		info.FieldCaml = SnakeToCaml(info.Field)
		info.JavaType = javaType
		infos = append(infos, info)
	}

	tableInfo := &TableInfo{
		DBTable:        tableName,
		Id:             id,
		IdCaml:         SnakeToCaml(id),
		Domain:         Ucfirst(strings.Trim(strings.Trim(tableName, "t_"), "sys_")),
		Fields:         infos,
		ContainsTime:   containsTime,
		ContainsArray:  containsIntArray || containsStringArray,
		LastFieldIndex: (len(infos) - 1),
	}
	return tableInfo

}

func Close(db *gorm.DB) error {
	sqldb, err := db.DB()
	if err != nil {
		return gorm.ErrUnsupportedDriver
	}
	sqldb.Close()
	return nil
}
