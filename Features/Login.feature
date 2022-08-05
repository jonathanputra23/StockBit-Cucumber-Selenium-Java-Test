Feature: StockBit login testing
I want to test the login function using
correct credentials and incorrect credentials

  Scenario: Test the login function with valid credentials
    Given I am on login page
    When I insert my correct credentials
    And I clicked log in
    Then Login successfully
    
  Scenario: Test the login function with invalid credentials
    Given I am on login page
    When I insert my incorrect credentials
    And I clicked log in
    Then Login failed