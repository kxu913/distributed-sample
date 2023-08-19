## Operation

**Use k8s+istio to deploy `Nacos`, `Sentinel`, `Seata`, and include all dashboards. I use K8s to manage this middlewares due to it easy for me to start them, and in my micro service I use domain instead of ip.**

- Create a namespace "operator" in k8s.

  `kubectl apply -f springcloud-ali/src/scripts/namespace.yaml`

- Create a mysql container in k8s.

  `kubectl apply -f springcloud-ali/src/scripts/mysql.yaml`

  *here is becuase nacos and seata support mysql in offical documents, I don't want do more refactor to make them support postgresql, so start a mysql database. Be sure that you mount mysql data folder on your local disk.*

- Create nacos in k8s

  `kubectl apply -f springcloud-ali/src/scripts/nacos.yaml`

  *mount application.properties in local disk aovid lost changes when restart k8s, persist configuration in mysql.*

- Create Sentinel dashboard in k8s.

  `kubectl apply -f springcloud-ali/src/scripts/sentinel-dashboard.yaml`

    - Download sentinel-dashboard.jar from offical site.
    - Create a Dockerfile, refrerence with *springcloud-ali/src/docker/Sentinel-Dashboard-Dockerfie*
    - Build a sentinel-dashboard image in local.

- Create Seata in k8s.

  `kubectl apply -f springcloud-ali/src/scripts/seata.yaml`

    - use nacos as seata server configuration.
    - create seata server configuration in nacos.

- Create Zipkin in k8s
  `kubectl apply -f springcloud-ali/src/scripts/zipkin.yaml`
  *Use mysql as persistent storage, need execute init/sql/zipkin.sql*

- Create Prometheus in k8s
  `kubectl apply -f springcloud-ali/src/scripts/promethenus.yaml`
  - make sure that micro services expose `/actuator/prometheus` api.
  - configure the micro services in `init/config/prometheus`.
  *Create another tool to fetch services config from Nacos, and call `reload` api to implement dynamic update prometheus*

- Create Grafana in k8s
  `kubectl apply -f springcloud-ali/src/scripts/grafana.yaml`
  - mount configuration.
  - mount data folder.

- Createa gateway for above services.

  `kubectl apply -f springcloud-ali/src/scripts/gateway.yaml`
  *There is an extra TCP gateway for seata 8091 port, mapped 8091->31400*

- Access Dashboards.
  - [Nacos - http://nacos.kevin.com/nacos/ ](http://nacos.kevin.com/nacos) 
  - [Sentinel -http://sentinel.kevin.com/](http://sentinel.kevin.com/#/dashboard/gateway/identity/gateway-service)
  - [Seata - http://seata.kevin.com](http://seata.kevin.com/#/globallock/list)
  - [Zipkin - http://zipkin.kevin.com/zipkin](http://zipkin.kevin.com/zipkin/)
  - [Prometheus - http://prometheus.kevin.com](http://prometheus.kevin.com/targets?search=)
  - [Grafana - http://grafana.kevin.com](http://grafana.kevin.com/?orgId=1)

- Snapshot
  - Nacos
  ![nacos.png](../public/nacos.png)
  - Sentinel
  ![sentinel](../public/sentinel.png)
  - Seata
  ![seata](../public/seata.png)
  - Zipkin
  ![zipkin](../public/zipkin.png)
  - Prometheus
  ![prometheus](../public/prometheus.png)
  - Grafana
  ![grafana](../public/grafana.png)






