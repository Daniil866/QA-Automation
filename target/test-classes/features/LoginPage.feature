Feature: Login Page functionality verification

  Scenario: Verify forgot password link redirect user to the reminder page
    Given User opens Home Page
    When User clicks on login button on Home Page
    And User clicks on forgot password link on Login Page
    And waits for 3 seconds
    Then verify url ends with "/reminder/"