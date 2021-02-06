# gaming-application

This microservice is written using Spring Boot
Java 8 features and Mysql database has been used
Swagger is used for documentation and Project Lombok is also used.

Below is snapshot of application.yml, make necessary configurations like setting up the database, prior to running the project.

        spring:
            application:
                name: gaming-app
                version: 1.0.0-SNAPSHOT
            datasource:
                driverClassName: com.mysql.jdbc.Driver
                url: jdbc:mysql://localhost:3306/gamingdb
                username: root
                password: Nidhi123@
            jpa:
                hibernate.ddl-auto: update
                generate-ddl: true
            servlet:
                multipart:
                    max-file-size: 15MB
                    max-request-size: 15MB

        server:
            port: 8090


To run this application: 
    Execute the following commands in order(from the root directory of the project)

        gradle clean build
        gradle bootRun


To access the Swagger UI of the application, hit the below url

        http://localhost:8090/

    