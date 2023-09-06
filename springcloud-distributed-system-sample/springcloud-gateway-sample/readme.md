## Integration
- Integrate with Nacos, here is some explanation about usage.
  - Not sure why dataId can't recognize by profile, I still need add a service name configuration in nocos server. here is another solution that use namespace and group to distinct different profiles.

- Integrate with Sentinel, here is explanation about usage use different rules.
  - Sample usage, that only use `flow` as rule, you don't need any special configuration, just use `SentialSource` sample code as below:
    ```
    flow:
      nacos:
        server-addr: kxu.kevin.com:80
        username: nacos
        password: 888888
        data-Id: ${spring.application.name}-flow-rules
        data-type: json
        rule-type: flow
    ```
  - Api group usage, it will be flexible than sample usage, but need do more configuration, steps:
    - Open ApiGroup module, you need set a system property `System.setProperty("csp.sentinel.app.type", ConfigConstants.APP_TYPE_SCG_GATEWAY);` in main class, there are some cache statics in Sential dashboard, it's better that restart the dashboard.
    - Add configurations into `application.yaml` for both `gw-api-group` and `gw-flow`, sample codes as below:
      ```
        gw-flow:
          nacos:
            server-addr: kxu.kevin.com:80
            username: nacos
            password: 888888
            data-Id: ${spring.application.name}-gw-flow-rules
            data-type: json
            rule-type: gw-flow
        gw-api-group:
          nacos:
            server-addr: kxu.kevin.com:80
            username: nacos
            password: 888888
            data-Id: ${spring.application.name}-api-group-rules
            data-type: json
            rule-type: gw-api-group
      ```
    - Configure an Api Group. see `resource/sential/gateway-service-api-groups.json`
    - Configure a rule base on Api Group. see `resource/sential/gateway-service-gw-flow-rules.json`
 