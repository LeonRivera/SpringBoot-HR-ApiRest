# SpringBoot-GenericRestCrudBP

Instructions 

1.- Open the scriptBd.sql  located in resources/scriptBd.sql and run it in MYSQL

2.- In the src/main/resources/application.properties modify the next lines with your values of mysql login

spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

3.- Open the root folder of the project and execute the command: mvn clean install

4.- Execute mvn-springboot:run 


------------------

Run Api Endpoints

Open postman or install it from https://www.postman.com/downloads/ 

In the field collections import the collection (.json) located in resources/JavaBackendL2.postman_collection.json

Run the different endpoints with data pre created 

------------------

Documentation URL of the api = http://localhost:8080/swagger-ui/index.html#/
