## The api is published on Railway platform, in case you don't want to set locally.
## Swagger - [https://studentapi.up.railway.app/swagger-ui/index.html#/student-controller]


# StudentApi
This project is an API created with Spring Boot. I have implemented the following requests:
- GET (ALL)
- GET BY ID
- POST
- PUT
- PATCH
- DELETE (ALL)
- DELETE BY ID


## Local Setup
- Install Java 8
- Install postgresql

When you have all of the above, clone this repository and just run StudentApiApplication. 

Please set this environment variables in IDE Edit Configurations
    DB_HOST
    DB_PORT
    DB_NAME
    DB_PASSWORD
    DB_USERNAME
    DB_DLL

* The port on which the app runs is 8085 (in my case for local). (you can change it from the application.yml)

* In case you don't want to create&drop the table each time, you must change in application.yml
this line -> ddl-aut: create-drop with ddl-auto:update

* For testing I would suggest to install Postman.
