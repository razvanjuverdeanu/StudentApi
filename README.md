

# StudentApi
This project is an API created with Spring Boot. I have implemented the following requests:
- GET (ALL)
- GET BY ID
- POST
- PUT
- PATCH
- DELETE (ALL)
- DELETE BY ID

## The api is published on Heroku platform, in case you don't want to set locally.
## More details can be found here -> https://studentapi-app.herokuapp.com/swagger-ui/index.html

# Local Setup
- Install Java 8
- Install postgresql

When you have all of the above, clone this repository and just run StudentApiApplication. 

* The port on which the app runs is 8085. (you can change it from the application.properties)

* In case you don't want to create&drop the table each time, you must change in application.properties
this line -> spring.jpa.hibernate.ddl-auto=create-drop with spring.jpa.hibernate.ddl-auto=update

* For testing I would suggest to install Postman.
