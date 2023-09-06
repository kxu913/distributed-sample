package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"html/template"
	"io"
	"net/http"
	"os"

	"github.com/robfig/cron/v3"
)

func GetEnvWithDefault(key string, defValue string) string {
	val, err := os.LookupEnv(key)
	if !err {
		return defValue
	}
	return val

}

type HostPair struct {
	Ip   string
	Port float64
}

type Host struct {
	Name string
	Ip   string
}

var (
	nacosApiHost      = GetEnvWithDefault("nacos_host", "http://nacos.kevin.com")
	prometheusApiHost = GetEnvWithDefault("prometheus_host", "http://prometheus.kevin.com")
	nacosUser         = GetEnvWithDefault("nacos_user", "nacos")
	nacosPassword     = GetEnvWithDefault("nacos_pwd", "888888")
	prometheusConfig  = GetEnvWithDefault("prometheus_config", "./prometheus.yaml")
	nacosLoginUrl     = fmt.Sprintf("%s/nacos/v1/auth/login?username=%s&password=%s", nacosApiHost, nacosUser, nacosPassword)
	nacosServicesList = fmt.Sprintf("%s/nacos/v1/ns/service/list?pageNo=0&pageSize=20", nacosApiHost)
	nacosInstances    = fmt.Sprintf("%s/nacos/v1/ns/instance/list?serviceName=", nacosApiHost)
	reloadConfigUrl   = fmt.Sprintf("%s/-/reload", prometheusApiHost)
)

func auth() string {
	request, err := http.NewRequest("POST", nacosLoginUrl, nil)
	if err != nil {
		panic(err)
	}
	response, err := http.DefaultClient.Do(request)
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()
	resBytes, err := io.ReadAll(response.Body)
	if err != nil {
		panic(err)
	}
	res := map[string]any{}
	json.Unmarshal(resBytes, &res)
	return res["accessToken"].(string)
}

func serviceList(token string) []any {
	request, err := http.NewRequest("GET", nacosServicesList, nil)
	if err != nil {
		panic(err)
	}
	request.Header.Add("Authorization", "Bearer "+token)
	response, err := http.DefaultClient.Do(request)
	if err != nil {
		panic(err)
	}
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()
	resBytes, err := io.ReadAll(response.Body)
	if err != nil {
		panic(err)
	}
	res := map[string]any{}
	json.Unmarshal(resBytes, &res)

	return res["doms"].([]any)
}

func instanceDetail(token string, service string) []HostPair {
	request, err := http.NewRequest("GET", nacosInstances+service, nil)
	if err != nil {
		panic(err)
	}
	request.Header.Add("Authorization", "Bearer "+token)
	response, err := http.DefaultClient.Do(request)
	if err != nil {
		panic(err)
	}
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()
	resBytes, err := io.ReadAll(response.Body)
	if err != nil {
		panic(err)
	}
	res := map[string]any{}
	json.Unmarshal(resBytes, &res)
	hosts := res["hosts"].([]any)
	hostPares := []HostPair{}
	for _, host := range hosts {
		instance := host.(map[string]any)
		hostPares = append(hostPares, HostPair{
			Ip:   instance["ip"].(string),
			Port: instance["port"].(float64),
		})

	}
	return hostPares
}

func hostPairToString(hs []HostPair) string {
	var bytes bytes.Buffer
	for index, h := range hs {
		bytes.WriteString(fmt.Sprintf("%s:%.0f", h.Ip, h.Port))
		if index < len(hs)-2 {
			bytes.WriteString(",")
		}
	}
	return bytes.String()
}

func reload() {
	request, err := http.NewRequest("POST", reloadConfigUrl, nil)
	if err != nil {
		panic(err)
	}
	http.DefaultClient.Do(request)
	fmt.Printf("call %s to reload.\r\n", reloadConfigUrl)
}

func updatePrometheusConfig(hosts []Host) {
	tpl, err := template.ParseFiles("./prometheus.yaml.tpl")
	if err != nil {
		panic(err)
	}
	os.OpenFile(prometheusConfig, os.O_CREATE, 0o666)
	file, err := os.OpenFile(prometheusConfig, os.O_RDWR, 0o666)
	if err != nil {
		panic(err)
	}
	tpl.Execute(file, hosts)

}

func startUpdating() {
	token := auth()
	services := serviceList(token)
	hosts := []Host{}
	for _, service := range services {
		instances := instanceDetail(token, service.(string))
		hosts = append(hosts, Host{
			Name: service.(string),
			Ip:   hostPairToString(instances),
		})
	}
	fmt.Printf("latest config is %s\r\n", hosts)
	updatePrometheusConfig(hosts)
	reload()

}

func main() {
	c := cron.New(cron.WithSeconds())
	spec := "0 */1 * * * *"
	c.AddFunc(spec, startUpdating)
	c.Start()

	for {
	}
}
