Feature: Main Page
As user I want to get Advertisers,Publishers and Top Level Clients list
  Background:
    Given site is opened
    And user is already logged in

  @advertiser @publisher @top_level_client
  Scenario: Open all subtrees
    When user clicks Advertiser block
    And user clicks Publishers block
    And  user clicks Top level clients block
    Then Advertisers list is shown
    And Publishers list is shown
    And Top level clients list is shown


  @advertiser
  Scenario: Advertiser block is shown
    When user clicks Advertiser block
    Then Advertisers list is shown
    And "Advertisers" is added to cookie "notSavedOpened"


  @advertiser
  Scenario: Hide advertiser block
    When user double clicks Advertiser block
    Then Advertisers list isn't shown
    And "Advertisers" isn't added to cookie "notSavedOpened"

  @publisher
  Scenario: Publishers block is shown
    When user clicks Publishers block
    Then Publishers list is shown
    And "Publishers" is added to cookie "notSavedOpened"

  @publisher
  Scenario: Hide Publishers block
    When user double clicks Publishers block
    Then Publishers list isn't shown
    And "Publishers" isn't added to cookie "notSavedOpened"

  @top_level_client
  Scenario: Top level clients block is shown
    When user clicks Top level clients block
    Then Top level clients list is shown
    And "Top level clients" is added to cookie "notSavedOpened"

  @top_level_client
  Scenario: Hide Top level clients block
    When user double clicks Top level clients block
    Then top level clients list isn't shown
    And "Top level clients" isn't added to cookie "notSavedOpened"
