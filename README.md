# UserMicroservice

A microservice that uses a REST API allows users to register, log in, retrieve a list of user items, and add user item. 
The service uses jwt token for authorization. Service uses Mysql database to store data.


Technologies used in app
<ol>
<li>Java 17</li>
<li>Mysql</li>
<li>JWT</li>
<li>Spring Boot</li>
<li>Lombok</li>
</ol>

How to run
<ol>
<li>Run MySQL server and create database with:</li>
  <ol>
    <li>name: usermicroservice<li>
    <li>port: 3306<li>
    <li>username: root<li>
    <li>password: root<li>
  </ol>
<li>Run UserMicroserviceApplication in IntelliJ</li>
<li>To test endpoints import spec.raml file to Postman</li>
<li>To test authorised endpoints copy token from response body from /login endpoint and paste to access token in authorization section</li>
</ol>


