# cryto-micro
https://github.com/WJ-Prajumsook/microservice-spring

Let's implement Microservices with Spring Boot and Spring Cloud.
Tech will be used in this tutorial:
- Spring Boot
- Spring cloud gateway
- Spring cloud Configuration server
- Spring cloud Configuration client
- Spring cloud NetFlix Eureka
- Spring cloud Circuit Breaker
- Spring cloud Actuator
- Keycloak
- Prometheus
- Grafana
- Beats/filebeat
- Logstash
- Elasticsearch
- Kibana

## Logs
### 22/01/2022
+ Create a github repository
+ summary: 
  + Config Service: github config(cryptosvc, currencysvc, futuresvc, eurekasvc)
    + git:
    + config folder: C:\Users\charl\IdeaProjects\springcloud\wj-prajumsook\cryto-micro\gitrepo\config-service
  + Eureka Service: server-client , register a client
  + Crypto-Service, Currency-Service, Future-Service: call external apies, serve rest api, circuit breaker
    + Lombok, Spring Web, Cloud Bootstrap, Config Client, Eureka Discovery Client (exclude starter-ribbon, ribbon-eureka) 
  + AuthService: 
    + Keycloak:
      + docker pull quay.io/keycloak/keycloak:15.0.2
      + docker run -d -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:15.0.2
        + api-realm: user - user, api-client - 9c3415a9-a68f-495d-9f6e-a7b6c1128ed7
    + crypto-service ++ changes: 
      + spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/auth/realms/api-realm
      + add package resource server
  + Monitoring: actuator metrics with Prometheus, Grafana
    + Config-service:
      + create Dockerfile: -> docker build -t config-service:1.0.0 .
        + mvn clean package
        + run ConfigSvc: docker run -d -p 8888:8888 config-service:1.0.0 .
        + change IP localhost -> IP 192.168.100.12 : in config EurekaSvc
        + docker build -t eureka-service:1.0.0 .
        + run EurekaSvc: docker run -d -p 7777:7777 eureka-service:1.0.0 .
        + docker build -t auth-service:1.0.0 .
        + run AuthSvc: docker run -d -p 6666:6666 auth-service:1.0.0 .
        + run AuthSvc: docker run -d -p 6666:6666 --label collect_logs_with_filebeat="true" --label decode_log_event_to_json_object="true" auth-service:1.0.0 .
        + 
        + docker build -t gateway-service:1.0.0 .
        + run GatewaySvc: docker run -d -p 9999:9999 gateway-service:1.0.0 .
        + 
        + docker build -t crypto-service:1.0.0 .
        + run : docker run -d -p 8077:8077 crypto-service:1.0.0 .
        + docker build -t currency-service:1.0.0 .
        + run : docker run -d -p 8088:8088 currency-service:1.0.0 .
        + docker build -t future-service:1.0.0 .
        + run : docker run -d -p 8099:8099 future-service:1.0.0 .



      + metrics:
        + add package actuator, micrometer
        + docker pull prom/prometheus
        + docker build -t prometheus_spring_boot .
        + add gitrepo/metrics/... : docker, prometheus config
        + docker run -d -p 9090:9090 prometheus_spring_boot
          + add security configuration in crypto service ++ -> permit actuator fix 401 UnAuth prometheus
        + docker pull grafana/grafana
        + docker run -d -p 3000:3000 grafana/grafana
        + localhost:3000 -> data source -> import
    + Logging:
      + auth-svc: add pakages: logstash-logback-encoder, cloud sleuth
        + add file logback-config.xml
        + add elastic folder: docker-compose.yml
        + run AuthSvc docker with --label to get ELK logging
      + http://localhost:5601/: start monitoring


### note
Eureka Server
http://localhost:7777/
eurekausername - eurekapassword

Keycloak
http://localhost:8080/
admin - admin

Elastic
http://localhost:5601/

Prometheus Targets
http://localhost:9090/targets


### build
cd ../auth-service
mvn clean package
docker rmi $(docker images 'auth-service' -a -q)
docker build -t auth-service:1.0.0 .

cd ../gateway-service
docker rmi $(docker images 'gateway-service' -a -q)
mvn clean package
docker build -t gateway-service:1.0.0 .

cd ../crypto-service
docker rmi $(docker images 'crypto-service' -a -q)
mvn clean package
docker build -t crypto-service:1.0.0 .


cd ../currency-service
docker rmi $(docker images 'currency-service' -a -q)
mvn clean package
docker build -t currency-service:1.0.0 .

cd ../future-service
docker rmi $(docker images 'future-service' -a -q)
mvn clean package
docker build -t future-service:1.0.0 .

### run
eureka, config, grafana, prometheus

elastic: docker-compose up -d

appcompose: docker-compose up -d

