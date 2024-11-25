Feature: User Management

  Scenario: Create a new user
    Given the API is up
    When I create a new user with valid details
    Then the user is created successfully

  Scenario: Get a user by username
    Given the API is up
    And a user with username "john_doe" exists
    When I retrieve the user with username "john_doe"
    Then the user details are returned successfully

  Scenario: Delete a user by username
    Given the API is up
    And a user with username "john_doe" exists
    When I delete the user with username "john_doe"
    Then the user is deleted successfully
