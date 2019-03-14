# BankingService

## Overview

The project was implemented using mainly `Java 8` as the backend language and `Spring Framework` for the integration of a dependency injection container that would initialize and manage the Java objects (which are called beans in Spring).

## Requirements
For building and running the application you need:
* JDK 1.8
* Maven 

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. 

- One way is to execute the main method in the com.blueharvest.demo.App class from your IDE.

- Alternatively you can use the Spring Boot Maven plugin like so:

```sh
mvn spring-boot:run
```

## Project structure

The project consists in two parts both located under `BankingService` directory:

- the backend, which is in `BankingService\src\main\java`
- the frontend which is in `BankingService\src\main\resources\static`

### Backend service

The backend implements the required API in order for the service to support the specified requirements.

#### Directory structure and components:

**_config_**

This is where the configuration components are defined. It defines the required beans in order for the application to function properly and the security http very simple implementation.

**_controller_**

Where the Spring MVC controllers are defined in order to handle the required requests.

`AccountController` handles `"/account"` path and defines the endpoint for creating a secondary account for an existing user.

```sh
HTTP request:
POST: /account/customerId/initialCredit
```
            
`HomeController` is a simple _Controller_ that will return the main html page of the application which is the `index.html`

```sh
HTTP request:
ANY REQUEST: "/"
```
          
`UserController` handles the user requests, like getting information about a particular user.

```sh
HTTP request:
GET: /user/id
GET: /user/login
    Params:
          username
          password
```          
                    
**_converter_**

* where the conversion between plain `model` objects and `dto` s is being defined.

**_data_**

* where the in-memory data is stored. It (more or less) acts like a relational database and it's implementing the CRUD and any other necesarry methods.

**_dto_**

* A _Data Transfer Object_ is an object that is used to encapsulate data, and send it from one subsystem of an application to another.

* In our application it is used so it can transfer the data to the client in the wanted format.

**_exception_**

* where the custom domain exceptions are defined.

**_model_**

* the plain models of the application defined for `Account`, `User` & `Transaction`.
It also defines an class `AccountTransactionsSummary` that combines account and transactions information and an enum for the type of accounts that can be created.

**_repository_** 

* the repositories are responsible for mapping the data from the in-memory storage to the business objects. It handles both how to read/write data (and also delete & update) from/to the storage.

**_service_**

* the purpose of service layer is to encapsulate business logic into a single place to promote code reuse and _SoC_ (separations of concerns).

**_utils_** 
* covers some utilitary classes.

### Frontend

The frontend(very basic) is implemented using `AngularJS`. I used plain old `HTML` as the template language and by using `AngularJS` I could automatically synchronize data from the UI (view) with the `JavaScript` objects (model) through 2-way data binding. 

All the logic is implemented in `js/app.js` where the angular module, the user controller and the services are defined.

#### Screenshot

###### Main Screen

At the moment there is only one user in memory
            
            username : harry
            password : potter
         
After login take care so you don't refresh the page because the user is not saved in a session and refreshing the page with reinstantiate the angular module.

![main-screen](./screenshots/main-screen.png)

###### Accounts Screen

For creating secondary accounts just add the initial credit in the credit input and click the 'Create account' button.

![accounts-screen](./screenshots/accounts-screen.png)
