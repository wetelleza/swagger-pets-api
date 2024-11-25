package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderSteps {

    private Response response;

    @Given("an order with ID {int} exists")
    public void ensureOrderExists(int orderId) {
        RestAssured.given().delete("/store/order/" + orderId);

        String body = """
        {
            "id": %d,
            "petId": 123,
            "quantity": 1,
            "shipDate": "2024-01-01T12:00:00Z",
            "status": "placed",
            "complete": true
        }
        """.formatted(orderId);

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/store/order")
                .then()
                .statusCode(200);
    }

    @When("I place an order for pet with ID {int}")
    public void placeOrder(int petId) {
        String body = """
        {
            "id": 2,
            "petId": %d,
            "quantity": 1,
            "shipDate": "2024-01-02T12:00:00Z",
            "status": "placed",
            "complete": true
        }
        """.formatted(petId);

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/store/order");
    }

    @Then("the order is placed successfully")
    public void verifyOrderPlaced() {
        response.then()
                .statusCode(200);
    }

    @When("I retrieve the order with ID {int}")
    public void getOrderById(int orderId) {
        response = RestAssured.given()
                .get("/store/order/" + orderId);
    }

    @Then("the order details are returned successfully")
    public void verifyOrderRetrieved() {
        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("status", equalTo("placed"));
    }

    @When("I delete the order with ID {int}")
    public void deleteOrder(int orderId) {
        response = RestAssured.given()
                .delete("/store/order/" + orderId);
    }

    @Then("the order is deleted successfully")
    public void verifyOrderDeleted() {
        response.then()
                .statusCode(200);
    }
}
