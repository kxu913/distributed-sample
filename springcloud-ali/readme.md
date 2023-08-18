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

- Createa gateway for above services.

  `kubectl apply -f springcloud-ali/src/scripts/gateway.yaml`
  *There is an extra TCP gateway for seata 8091 port, mapped 8091->31400*

- Update configration in micro services.
    ```
    spring.cloud.nacos.*.server-addr=nacos.kevin.com:80
    *.grouplist = "seata.kevin.com:31400"
    ```

- Access Dashboards.
    ```
    http://nacos.kevin.com/nacos/#/configeditor?serverId=center&dataId=seataServer&group=DEFAULT_GROUP&namespace=557c06e3-dff2-4448-94ce-6b6c4a7083be&edasAppName=&edasAppId=&searchDataId=&searchGroup=&pageSize=10&pageNo=1

    http://sentinel.kevin.com/#/dashboard/gateway/identity/gateway-service

    http://seata.kevin.com/#/globallock/list
    ```






