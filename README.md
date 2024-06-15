# Employee Bonus Application

Welcome to the Employee Bonus Application! This application provides functionality for managing employee data and determining bonus eligibility based on specific criteria. It provides RESTful APIs for adding employees and retrieving those eligible for bonuses on a given date.

### Features

* Save employee data to a database
* Retrieve employees eligible for bonuses on a specified date
* Associate employees with departments
* Comprehensive error handling for unexpected scenarios

### Project Structure
* Controller Layer: Handles incoming HTTP requests.
* Service Layer: Contains business logic and interacts with repositories for data access.
* Model Layer: Defines entities Employee and Department and their relationships.
* Exception: Provides exception handling for better error reporting.


### Installation & Getting Started

To run the application, follow these steps:

* Open the project in your preferred Java IDE
* Run the application



### Usage

* Save Employee Data
````
Endpoint: POST /tci/employee-bonus
Description: Saves employee data to the database from a JSON payload
````

* Retrieve Eligible Employees for Bonuses
````
Endpoint: GET /tci/employee-bonus?date=<dateString>
Description: Retrieves employees eligible for bonuses based on the specified date
````

### API Endpoints

````
POST  http://localhost:8888/tci/employee-bonus
GET   http://localhost:8888/tci/employee-bonus?date=may-27-2022
````



### Technology Stack

- Java
- Spring Boot
- H2 Database
- Lombok for reducing boilerplate code
