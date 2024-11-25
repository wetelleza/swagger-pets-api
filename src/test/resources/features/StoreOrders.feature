Feature: Store Orders

  Scenario: Place an order
    Given the API is up
    When I place an order for pet with ID 123
    Then the order is placed successfully

  Scenario: Get an order by ID
    Given the API is up
    And an order with ID 1 exists
    When I retrieve the order with ID 1
    Then the order details are returned successfully

  Scenario: Delete an order by ID
    Given the API is up
    And an order with ID 1 exists
    When I delete the order with ID 1
    Then the order is deleted successfully
