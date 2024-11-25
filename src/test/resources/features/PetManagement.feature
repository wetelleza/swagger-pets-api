Feature: Pet Management

  Scenario: Add a new pet to the store
    Given the API is up
    When I add a new pet with valid details
    Then the pet is added successfully

  Scenario: Update an existing pet
    Given the API is up
    When I update an existing pet with valid details
    Then the pet is updated successfully

  Scenario: Get a pet by ID
    Given the API is up
    When I retrieve the pet with ID 1
    Then the pet details are returned successfully

  Scenario: Delete a pet by ID
    Given the API is up
    When I delete the pet with ID 123
    Then the pet is deleted successfully

  Scenario: Get pets by status
    Given the API is up
    When I retrieve pets with status "available"
    Then a list of "available" pets is returned

  Scenario: Add a pet with invalid ID
    Given the API is up
    When I add a new pet with an invalid ID
    Then an error message is returned