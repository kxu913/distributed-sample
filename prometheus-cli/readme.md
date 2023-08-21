## Prometheus-cli project

This project used to integrate with Nacos to dynamic update targets.

### Implementation

The Cli calls Nacos api to get targets information which registed in Nacos, then update prometheus.yaml, finally call prometheus api to reload configuration.

### How to use it

- create a docker image.
`docker build -t prometheus-cli .`

- deploy the cli with nacos and prometheus, config below envs.
    - **nacos_host**
    - **nacos_user**
    - **nacos_pwd**
    - **prometheus_host**

### Attentions

The cli run in every mintue, and will generate a new prometheus.yaml file, so please make sure that `prometheus.yaml.tpl` contains your configurations.
