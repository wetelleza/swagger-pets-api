# API Automation Testing for Swagger Petstore

This repository contains the API automation test suite for the **Swagger Petstore API**. The tests are implemented using **RestAssured**, **Cucumber**, and **Serenity BDD** to ensure comprehensive coverage, clean reporting, and seamless integration with CI/CD pipelines.

---

## **Technologies Used**
- **Java**: Programming language for implementing the test suite.
- **RestAssured**: For API testing and validation.
- **Cucumber**: Behavior-Driven Development (BDD) framework.
- **Gherkin**: For writing human-readable feature files.
- **Serenity BDD**: For test reporting and result visualization.
- **Maven**: Build and dependency management.
- **JUnit**: Test runner integration with Serenity and Cucumber.

---

## **Prerequisites**
1. **Java 17+** installed on your system.
2. **Maven** for dependency management and build.
3. **ChromeDriver** if browser interactions are needed.
4. **Local Petstore API Server** running at `http://localhost:8080/api/v3`.

---

## **Setup and Execution**

1. Clone the repository from GitHub and navigate to the project directory.

2. Install the dependencies using Maven by running the `mvn clean install` command in your terminal.

3. To execute the tests and generate reports, use the command `mvn clean verify`. This will run all the tests and produce detailed Serenity BDD reports.

4. After the execution, the Serenity HTML report can be found at `target/site/serenity/index.html`. Open this file in a web browser to view the results.

---

## **Features Implemented**

### **Feature 1: Pet Management**
This feature automates the CRUD operations for pets in the store, ensuring the APIs perform correctly under various scenarios. The scenarios implemented include:
- Adding a new pet to the store using the `POST /pet` endpoint.
- Updating an existing pet’s details using the `PUT /pet` endpoint.
- Retrieving a pet by its ID using the `GET /pet/{petId}` endpoint.
- Deleting a pet using the `DELETE /pet/{petId}` endpoint.
- Fetching a list of pets by their status using the `GET /pet/findByStatus` endpoint.

### **Example Scenario: Adding a New Pet**
A scenario is written in Gherkin syntax for adding a new pet. It validates that the API responds with a `200` status and the correct pet details. The test suite contains step definitions that implement the logic for interacting with the API endpoints and validating responses.

---

## **Reporting**
The project uses Serenity BDD for detailed test reporting. Serenity provides:
- Execution results for each feature and scenario.
- A breakdown of steps within each scenario, including pass/fail status.
- Screenshots for each step (if enabled), making it easier to debug and understand test failures.

To view the reports, navigate to `target/site/serenity/index.html` in your project directory after running the tests. Open the file in a browser to access a user-friendly dashboard summarizing the test execution.

---
