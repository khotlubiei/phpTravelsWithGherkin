Feature: HotelsSearch

  Scenario: Submitting the Hotel Search with data specified by IDEXX

    Given I am on the "home" page
    When I search for hotels using following details
      | location                   | From (Days from today) | To (Days from today) | Adults | Children |
      | Rendezvous Hotel Singapore | 5                      | 10                   | 2      | 1        |
    Then search succeeds
    And i can see the price of the "Junior Suites" is "1250"
    And i can see the reviews from people and "Beano"

