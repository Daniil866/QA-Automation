Feature: Product page verification

  Scenario: Verify user can be navigated to the Product Page
    Given User opens Home Page
    When User enters "xBox" word into search filed on Home Page
    And User clicks on search button on Home Page
    And User clicks on 2 product
    Then verify user is navigated to the correct Product Page