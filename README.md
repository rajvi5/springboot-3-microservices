# springboot-3-microservices
springboot-3-microservices.

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

References:
1. https://www.youtube.com/playlist?list=PL0zysOflRCelb2Y4WOVckFC6B050BzV0D
2. https://www.youtube.com/watch?v=HFl2dzhVuUo&t=768s
3. https://www.youtube.com/watch?v=yKZVdkrTBTg