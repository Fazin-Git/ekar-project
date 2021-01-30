# Ekar producer consumer API
Simple Spring boot application which takes input from the client with number of producers and consumers. 
Based on the inputs a counter value has to be incremented for producer and decremented for consumer.
Second end point takes the value from client to resent the counter.

### Prerequisite
- Maven
- OpenJDK 1.8+
- Docker
- MySql
- Lombok
### Project Structure
```bash
producer-consumer-service
├── src
│   ├── main
│   │   ├── java\com\ekar\test\app
│   │   └── resources
│   └── test
│       ├── java\com\ekar\test\app
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md
```
##### Execute below commands to run the project
- mvn clean package
- docker-compose up --build
##### Once application is up and running use below postman collection from git repo.
- Ekar.json
- Port: 8086
##### Execute below steps in another terminal to verify the DB entries
- docker exec -it mysql-standalone /bin/bash
- mysql -usa -ppassword -h localhost -P3306
- show databases;
- use test;
- show tables;
- select * from counter_details;
- select * from request_response_log;

### Basic API Information
| Method | Path | Usage |
| --- | --- | --- |
| POST | api/v1/threads-count | Configure producer and consumer threads |
| PUT | api/v1/reset-counter | API to reset the counter value |
##### Swagger-UI
API Specification is provided in the [swagger-ui page](http://localhost:8086/swagger-ui.html) after spring boot application start.
```
http://localhost:8086/swagger-ui.html
```
##### Error Code
```
| ERR_SYS_001 | when counter value is exhausted |
```
#### Output Screen shots
