# Logs
14/12/21
https://www.youtube.com/watch?v=lhEPJRZkbrw
PART 1 - Implementing Microservices with Spring Boot and Spring Cloud
- Create Services: crypto, currency, future 
-- add pom depencencies spring cloud starter bootstrap
-- restTemplate call external website: yahoo finance

- Implementing circuit breaker with Spring CLoud Circuit Breaker
-- add pom spring-cloud-starter-circuitbreaker-resilience4j
-- add circuit breaker factory Bean -> Autowire at Service -> use CB when call external url

- Centralized Configuration with Spring Cloud Configuration
-- Separating configuration from service code
-- create github repo -> create config-service -> add config-server dependency -> add config properties: local,dev,prod
-- add  pom spring-cloud-starter-config -> add config properties -> use dev profiles

- Next Eureka-Server, KeyCloak, Authen-Service, File Beat, ELK, Prometheus, Grafana, API Gateway, Docker, Test? >.<