## Docker setup

 1. Install docker desktop
 2. Open a terminal and run the following commands: 
 	a) docker pull postgres
	b) docker pull rjuverdeanu/studentapiapp:new_db_on_start
 3. If you type docker images you should see 2 pulled images
 4. Now we have to run the images:
  a) Before starting postgres make sure you have a folder named postgres_container_data 
  - Run this command: docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=Student -v C:\postgres_container_data:/var/lib/postgresql/data --name=postgres_con postgres
  - Run in terminal docker ps or check in docker desktop, in Containers tab, that you have postgres_con container up and running
  b) Starting Student API  
  - Run this command: docker run -d -p 8085:8085 --name=studentapi_con rjuverdeanu/studentapiapp:new_db_on_start
  
  - Run in terminal docker ps or check in docker desktop, in Containers tab, that you have studentapi_con container up and running 
  (it will take around 20 seconds to be in run mode - you can click on studentapi_con in docker desktop in order to see logs)
  
 5.  Once everything is running you should be able to see swagger by accessing http://localhost:8085/swagger-ui/index.html#/student-controller



 
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
   - DB_HOST
   - DB_PORT
   - DB_NAME
   - DB_PASSWORD
   - DB_USERNAME
   - DB_DLL

* The port on which the app runs is 8085 (in my case for local). (you can change it from the application.yml)

* In case you don't want to create&drop the table each time, you must change in application.yml
this line -> ddl-aut: create-drop with ddl-auto:update

* For testing I would suggest to install Postman.
