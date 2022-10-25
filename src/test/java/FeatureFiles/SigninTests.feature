## Created by akashgupta at 21/10/22
#Feature: Signin Tests
#  Signup, SignIn and SignOut Tests
#
#  Scenario: User should be able to sign up
#    Given I am on the home page
#     When I signup using a new user
#     Then I verify I am logged in successfully after signup
#
#  Scenario: User should be able to sign in and sign out
#    Given I am on the home page
#     And  I signin using a registered user
#     And  I verify I am logged in successfully
#     And  I navigate to Settings page
#     When I click on logout button
#     Then I verify I am logged out successfully