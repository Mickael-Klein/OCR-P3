# ChâTop Spring Boot API project

This repository contains a Spring Boot API for the ChâTop project, implementing user authentication, data storage, and resource access. The API is designed to work in conjunction with the ChâTop Front-End, whose repository can be found [here](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring).

## Table of Contents

- [ChâTop Spring Boot API Project](#châtop-spring-boot-api-project)
  - [Contents](#table-of-contents)
  - [Prerequisite Requirements](#prerequisite-requirements)
  - [Configuration Settings](#configuration-settings)
  - [Installation Guide](#installation-guide)
  - [Project Architecture](#project-architecture)
  - [Required Dependencies](#required-dependencies)
  - [Security and Authentication](#security-and-authentication)
  - [Management of Images](#management-of-images)
  - [API documentation](#api-documentation)
  - [Additional Informations](#additional-informations)

## Prerequisite Requirements

Before you begin setting up the ChâTop Spring Boot API, make sure your system satisfies the following prerequisites:

- **Java Development Kit (JDK):** Download and install the latest version of the [JDK](https://adoptopenjdk.net/) suitable for your platform.

- **Apache Maven:** This project relies on [Maven](https://maven.apache.org/) for managing dependencies and building. Install Maven to facilitate project setup and maintenance.

- **MySQL:** This project relies on a MySQL database. If you haven't installed MySQL yet, refer to the installation guide [here](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/).

Ensure these prerequisites are met before proceeding with the configuration settings steps.

## Configuration Settings

### 1. Java

**Setting up Java Environment Variable:**

Ensure that the Java environment variable is correctly configured on your system. This variable is crucial for the proper execution of Java applications. Follow the steps relevant to your operating system:

#### Windows:

1. Open the System Properties.
2. Navigate to the `Advanced` tab.
3. Click the `Environment Variables` button.
4. Under `System variables`, scroll down to find the `Path` variable and click `Edit`.
5. Add the path to your JDK's binary directory (e.g., `C:\Program Files\Java\jdk[VERSION]\bin`) to the list of values. Ensure to separate it from other entries with a semicolon.
6. Click `OK` to save your changes and run the following command:

```shell
java -version
```

You should see information about the installed Java version.

### 2. MySQL Database

Follow these steps to configure MySQL Workbench for your Java application:

1. Open MySQL Workbench.
2. Connect to your MySQL Server instance.
3. Create a new database for your application:

```sql
CREATE DATABASE `dbName`;
```

If you're looking for inspiration for the database name, consider using `chatop`, it's a solid choice !

## Installation Guide

**Cloning the project:**

1. Clone this repository from GitHub: `git clone https://github.com/Mickael-Klein/OpenClassRooms-Dev-FullStack-Projet_3.git`

**Configure environment variables in the application.properties file**

### 2. Configuring the `application.properties` file:

The project includes an `application.properties` file located in the `src/main/resources/` folder.
Here are the environment variable elements you need to set up:

```properties
# Port on which the application will be deployed
server.port=3001
```

The `server.port` is setted up on port `3001`, you can change this value if needed.

```properties
# Data source configuration (MySQL)
spring.datasource.url=jdbc:mysql://localhost:{port}/{dbName}
spring.datasource.username={username}
spring.datasource.password={password}
```

- The `spring.datasource.url` MySQL default `{port}` is usually `3306`.
- The `spring.datasource.url` MySQL `{dbName}` is the name of the database you created during the Configuration Settings for MySQL.
- The `spring.datasource.username` and `spring.datasource.password` are your MySQL credentials.

```properties
# Directory for uploading rental pictures
upload-dir={C:/directoryPathToProject}/Backend/chatop/src/main/resources/static/rentalPictures
```

The `upload-dir` must contain the path to your parent project folder followed by /Backend/chatop/src/main/resources/static/rentalPictures. This indicates the server where rental pictures will be stored.

```properties
# Secret key for JWT (Json Web Token)
jwtKey={JWT key}
```

The `jwtKey` must contain your JWT encryption key, which will be used for authentication purposes. Use a strong 256-bit key and keep it confidential.

3. Run the application using your IDE or by running `mvn spring-boot:run` in the project directory.

4. Access the Swagger-ui to explore and test the API, the url to access it is : `http://localhost:3001/swagger-ui.html` if you run the application locally and did not change the running server port.

5. You can also use Postman to test API calls, Postman collection can be found in the `/Backend/Resources/postman` folder.

6. You can clone the Front-End Angular application [here](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring), follow its installation and running process, and then make it work with your API.

## Project Architecture

The project adheres to a conventional layered architecture (Controller/Service/Java Persistence API Repository) to ensure the modularity and maintainability of the codebase, aligning with industry best practices.

```
├───main
│   ├───java
│   │   └───com
│   │       └───chatop
│   │           ├───chatopApiController
│   │           ├───chatopApiDTO
│   │           ├───chatopApiModel
│   │           ├───chatopApiRepository
│   │           ├───chatopApiService
│   │           ├───configuration
│   │           ├───Interface
│   │           │   ├───ChatopApiServiceInterface
│   │           │   ├───UtilCommonInterface
│   │           │   ├───UtilEntityAndDTOCreationInterface
│   │           │   │   └───FactoryInterface
│   │           │   └───UtilResponseInterface
│   │           └───utils
│   │               ├───Common
│   │               ├───EntityAndDTOCreation
│   │               │   └───Factory
│   │               ├───RequestInput
│   │               ├───ResponseComponent
│   │               └───SwaggerApiResponse
│   └───resources
│       ├───static
│       │   └───rentalPictures
│       └───templates
└───test
    └───java
        └───com
            └───chatop
```

## Required Dependencies

1. **Spring Boot Starters:**

   - `spring-boot-starter-data-jpa`
   - `spring-boot-starter-security`
   - `spring-boot-starter-web`

2. **Database Connector:**

   - `mysql-connector-j (runtime scope)`

3. **Project Lombok:**

   - `lombok (optional)`

4. **Testing Dependencies:**

   - `spring-boot-starter-test (test scope)`
   - `spring-security-test (test scope)`

5. **OAuth2 Resource Server:**

   - `spring-boot-starter-oauth2-resource-server (version: 2.7.5)`

6. **JSON Web Token (JWT) Dependencies:**

   - `jjwt-api (version: 0.11.2)`

7. **Validation Dependency:**

   - `hibernate-validator (version: 8.0.0.Final)`

8. **ImageIO Dependency:**

   - `webp-imageio (version: 0.1.6)`

9. **Swagger Dependencies:**
   - `springdoc-openapi-starter-webmvc-ui (version: 2.3.0)`

## Security and Authentication

Authentication is managed by Spring Security with `Oauth2 Resource Server` and JWT. All routes require authentication, except those related to account creation or login. Passwords are encoded with `Bcrypt` and securely stored in the database.

## Management of Images

When creating a rental, an image is mandatory. This image is submitted to the API, stored on the server in the `/src/main/resources/static/rentalPictures` folder, and the image URL is persisted in the database. When a client retrieves a rental or a list of rentals, the `UrlGeneratorComponent` in the `utils/Common` folder dynamically generates the final client URL to access the image.

## API Documentation

The API is documented using Swagger. To access the API documentation, navigate to the Swagger URL after running the server: `http://localhost:3001/swagger-ui/index.html`.

Alternatively, you can review the available routes and endpoints below:

**Authentication and Authorization:**

For security reasons, all routes (endpoints) require a valid JWT (Bearer Token) in the Authorization header for access, except the login and register endpoints.

**Auth Routes**

- **POST /api/auth/register**

  - Parameters: None
  - Request Payload: { email: string; name: string; password: string; }
  - Response Payload: { token: string }
  - Description: Register a new user and receive a JSON Web Token.
  - Authorization Header: Not required for this route.

- **POST /api/auth/login**

  - Parameters: None
  - Request Payload: { email: string; password: string; }
  - Response Payload: { token: string }
  - Description: Log in and receive a JSON Web Token.
  - Authorization Header: Not required for this route.

- **GET /api/auth/me**
  - Parameters: None
  - Request Payload: None
  - Response Payload: { id: number, name: string, email: string, created_at: Date, updated_at: Date }
  - Description: Get user (himself) information.
  - Authorization Header: Bearer Token required.

**User Routes:**

- **GET /api/user/{id}**
  - Parameters: id (number)
  - Request Payload: None
  - Response Payload: { id: number, name: string, email: string, created_at: Date, updated_at: Date }
  - Description: Get user information by user id.
  - Authorization Header: Bearer Token required.

**Messages Routes:**

- **POST /api/messages**
  - Parameters: None
  - Request Payload: { rental_id: number, user_id: number, message: string }
  - Response Payload: { message: string }
  - Description: Post a message for a specific rental.
  - Authorization Header: Bearer Token required.

**Rentals Routes:**

- **GET /api/rentals**

  - Parameters: None
  - Request Payload: None
  - Response Payload: Array of rentals { id, name, surface, price, picture, description, owner_id, created_at, updated_at }[]
  - Description: Get a list of all rentals.
  - Authorization Header: Bearer Token required.

- **GET /api/rentals/{id}**

  - Parameters: id (number)
  - Request Payload: None
  - Response Payload: Rental object { id, name, surface, price, picture, description, owner_id, created_at, updated_at }
  - Description: Get details of a specific rental.
  - Authorization Header: Bearer Token required.

- **POST /api/rentals**

  - Parameters: None
  - Request Payload: FormData object { name: string, surface: number, price: number, picture: image file, description: string }
  - Response Payload: { message: string }
  - Description: Add a new rental.
  - Authorization Header: Bearer Token required.

- **PUT /api/rentals/{id}**
  - Parameters: id (number)
  - Request Payload: FormData object { name: string, surface: number, price: number, description: string }
  - Response Payload: { message: string }
  - Description: Modify an existing rental.
  - Authorization Header: Bearer Token required.

## Additional Information

- The `webp-imageio (version: 0.1.6)` dependency enables the handling of WebP image files.
- The `script.sql` will be launched automatically at each server start, ensuring the required tables exist in the database.
- The `springdoc-openapi-starter-webmvc-ui (version: 2.3.0)` dependency ensures compatibility with Spring Boot 3 for Swagger generation.
