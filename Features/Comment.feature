Feature: StockBit comment testing
I want to test the post comment function and
delete comment function

  Scenario: Test the post stream function
    Given I am on login page
    When I insert my correct credentials
    And I clicked log in
    And Login successfully
    When I post a comment
    Then Comment successfully posted

  Scenario: Test the delete stream function
    Given I am on login page
    When I insert my correct credentials
    And I clicked log in
    And Login successfully
    And I go to my profile
    And I delete my latest comment
    Then Stream comment will be deleted