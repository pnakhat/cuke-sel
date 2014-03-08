Feature: Registration

  Scenario: Valid User Registers at website

    Given I have some user waiting to be registered
      | email               | password | confirmation |
      | pna122@gmail.com    | test1234 | test1234      |

    Given I navigate to spree portal
    When I register the user with the given detail
    Then users should be registered successfully


  Scenario: Valid user registers at website

    Given I have a valid user details
    Given I navigate to spree portal
    When I register the user
    Then users should be registered successfully

  @registration
  Scenario: Invalid Registration
    Given I am a user and I choose less then six character long password
    And I navigate to spree portal
    When I register the user
    Then user should not be registered
    And I should be told that password is less the six characters long

