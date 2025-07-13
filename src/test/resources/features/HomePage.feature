Feature: Home Page functionality verification

  Scenario Outline: Verify user can find products using search field
    Given User opens Home Page
    When User enters "<wordToSearch>" word into search filed on Home Page
    And User clicks on search button on Home Page
    Then verify Search Result Page title contains "<wordToSearch>" word

    Examples:
      | wordToSearch |
      | iPhone       |
      | xBox         |