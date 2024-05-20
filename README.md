# springboot-3-microservices
springboot-3-microservices.

Services Intro:
1. Department Service and Employee Service
2. Config server - common place that contains all the configurations for both the services
3. Service Registry - Eureka client used to register Department and Employee service
4. Api Gateway - common point that maps http request with department and employee service. Hence instead of using different url(consisting different ports) for both the services everytime, letting gateway navigate the request to specific service

Points covered:
1. Microservice discovery using Eureka
2. Communication using RestTemplate
3. Communication using WebClient
4. Communication using FeignClient
5. Implementation of config server
6. Implementation of Api Gateway
7. Microservice health check using Actuator - department service

References:
1. https://www.youtube.com/playlist?list=PL0zysOflRCelb2Y4WOVckFC6B050BzV0D
2. https://www.youtube.com/watch?v=HFl2dzhVuUo&t=768s
3. https://www.youtube.com/watch?v=yKZVdkrTBTg
4. https://www.youtube.com/watch?v=OTSDm51vli8

Running 2 spring boot instances:

Helps in mitigating the risk of single point failure of an application
Refer department-service.yaml where two default zones for eureka client registration are specified.
1. Goto services below and right click and copy configurations 
2. Right click on new instance and click on edit configurations  
3. Add -Dserver.port=8085 in VM options.

Refer: https://stackoverflow.com/questions/58348457/running-two-spring-boot-instances