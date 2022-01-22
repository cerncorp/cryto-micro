# cryto-micro

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
        + docker run -d -p 8888:8888 config-service:1.0.0 .
        + change IP localhost -> IP 192.168.100.12 : in config EurekaSvc
