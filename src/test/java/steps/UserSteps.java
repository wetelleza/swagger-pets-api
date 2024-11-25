package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.*;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    private Response response;

    @Given("a user with username {string} exists")
    public void ensureUserExists(String username) {
        RestAssured.given().delete("/user/" + username);

        String body = """
        {
            "id": 1,
            "username": "%s",
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "password": "password",
            "phone": "123456789",
            "userStatus": 1
        }
        """.formatted(username);

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/user")
                .then()
                .statusCode(200);
    }

    @When("I create a new user with valid details")
    public void createUser() {
        String body = """
        {
            "id": 2,
            "username": "jane_doe",
            "firstName": "Jane",
            "lastName": "Doe",
            "email": "jane.doe@example.com",
            "password": "password",
            "phone": "987654321",
            "userStatus": 1
        }
        """;

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/user");
    }

    @Then("the user is created successfully")
    public void verifyUserCreated() {
        response.then()
                .statusCode(200);
    }

    @When("I retrieve the user with username {string}")
    public void getUserByUsername(String username) {
        response = RestAssured.given()
                .get("/user/" + username);
    }

    @Then("the user details are returned successfully")
    public void verifyUserRetrieved() {
        response.then()
                .statusCode(200)
                .body("username", equalTo("john_doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @When("I delete the user with username {string}")
    public void deleteUser(String username) {
        response = RestAssured.given()
                .delete("/user/" + username);
    }

    @Then("the user is deleted successfully")
    public void verifyUserDeleted() {
        response.then()
                .statusCode(200);
    }
}
