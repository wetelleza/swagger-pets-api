package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;


public class PetSteps {

    private Response response;

    @Given("the API is up")
    public void theApiIsUp() {
        RestAssured.baseURI = "http://localhost:8080/api/v3";
    }

    @When("I add a new pet with valid details")
    public void addNewPet() {
        String body = """
        {
            "id": 123,
            "name": "Buddy",
            "photoUrls": ["http://example.com/photo1"],
            "status": "available"
        }
        """;

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
    }

    @Then("the pet is added successfully")
    public void verifyPetAdded() {
        response.then()
                .statusCode(200)
                .body("name", equalTo("Buddy"));
    }

    @When("I update an existing pet with valid details")
    public void updatePet() {
        String body = """
    {
        "id": 123,
        "name": "BuddyUpdated",
        "photoUrls": ["http://example.com/photo2"],
        "status": "sold"
    }
    """;

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("/pet");
    }

    @Then("the pet is updated successfully")
    public void verifyPetUpdated() {
        response.then()
                .statusCode(200)
                .body("name", equalTo("BuddyUpdated"))
                .body("status", equalTo("sold"));
    }

    @When("I retrieve the pet with ID {int}")
    public void getPetById(int petId) {
        response = RestAssured.given()
                .get("/pet/" + petId);
    }

    @Then("the pet details are returned successfully")
    public void verifyPetRetrieved() {
        response.then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @When("I delete the pet with ID {int}")
    public void deletePetById(int petId) {
        response = RestAssured.given()
                .delete("/pet/" + petId);
    }

    @Then("the pet is deleted successfully")
    public void verifyPetDeleted() {
        response.then()
                .statusCode(200);
    }

    @When("I retrieve pets with status {string}")
    public void getPetsByStatus(String status) {
        response = RestAssured.given()
                .queryParam("status", status)
                .get("/pet/findByStatus");
    }

    @Then("a list of {string} pets is returned")
    public void verifyPetsByStatus(String status) {
        response.then()
                .statusCode(200)
                .body("status", hasItem(status));
    }

    @When("I add a new pet with an invalid ID")
    public void addPetWithInvalidID() {
        String body = """
    {
        "id": "s",
        "name": "doggie",
        "category": {
            "id": 1,
            "name": "Dogs"
        },
        "photoUrls": ["string"],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
    }
    """;

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
    }


    @Then("an error message is returned")
    public void verifyErrorMessage() {
        response.then()
                .statusCode(400); // o el código de error que la API devuelva
    }

}
