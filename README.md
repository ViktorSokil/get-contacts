#Development 
#To start 
 your application in the dev profile, simply run using terminal: 
 ./mvnw or mvnw
 
 #To create test db
 You should create schema in your local db:
 url: jdbc:postgresql://localhost:5432/postgres
 Then change username and password in application-dev.
 Start application and liquibase create table automatically. Then 
 Then ran script to populate random test data:
 insert into contact values ( generate_series(1,1000000), md5(random()::text));
 commit;
 
#To test
 You could use swagger:
 http://localhost:8080/swagger-ui.html#!/
 
 #Main Classes with logic for task 
 domain.Contact
 domain.ListContactsWrapper
 repository.ContactRepository
 service.impl.ContactServiceImpl
 web.rest.ContactResource
 
 
Latest:

| Status | Project | Branch | Description
| :----- | :------ | :--- | :---
| [![Build Status](https://api.travis-ci.com/ViktorSokil/get-contacts.svg?branch=master)](https://travis-ci.com/ViktorSokil/get-contacts#) | get-contact | Main build process.

 








