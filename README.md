# UserMicroservice

A microservice that uses a REST API allows users to register, log in, retrieve a list of user items, and add user item. 
The service uses jwt token for authorization. Service uses Mysql database to store data.


Technologies used in app
<ol>
<li>Java 17</li>
<li>Mysql</li>
<li>JWT</li>
<li>Spring Boot</li>
<li>Lombok/li>
</ol>

How to run
1.Run MySQL server and create database with:
  name: usermicroservice
  port: 3306
  username: root
  password: root
2.Run UserMicroserviceApplication in IntelliJ
3.To test endpoints import spec.raml file to Postman.
4.To test authorised endpoints copy token from response body from /login endpoint and paste to access token in authorization section.


