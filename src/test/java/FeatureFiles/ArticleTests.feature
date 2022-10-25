# Created by akashgupta at 21/10/22
Feature: Articles Tests
  Tests for posting articles, commenting, adding to favourite, etc.

  @PublishArticle
  Scenario: User should be able to post articles with all the inputs mentioned
    Given I am on the home page
     And I signin using a registered user
    When I publish a new article
    Then I verify that the article is published
     And I delete the published article

#  Scenario: User should be able to favorite any article
#    Given I am on the home page
#     And  I signin using a registered user
#     And I select "welcome" from popular tag list
#     And I select the first article from the list
#    When I favourite the article
#    Then I verify that the article is favourited
#
#  Scenario: User should be able to comment on any articles
#    Given I am on the home page
#     And  I signin using a registered user
#     And I select "welcome" from popular tag list
#     And I select the first article from the list
#    When I post a comment "test comment" on the article
#    Then I verify that the comment "test comment" is posted successfully
#     And I delete the comment

  @PublishArticle
  Scenario: User should be able to edit any article
    Given I am on the home page
    And  I signin using a registered user
    And I publish a new article
    When I edit the published article
    Then I verify that the article is published after editing
    And I delete the published article

#  Scenario: User should be able to follow an author
#    Given I am on the home page
#     And  I signin using a registered user
#     And I select "welcome" from popular tag list
#     And I select the first article from the list
#    When I follow the author of the article
#    Then I verify that the user under test is following the author




