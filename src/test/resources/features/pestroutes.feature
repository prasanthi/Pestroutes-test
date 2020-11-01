#Author: prasanthi.charm@gmail.com
#Summary : Create a Customer and validate the details
@tag
Feature: Customer creation

  @tag1
  Scenario Outline: Validate customer creation
    Given I sign in to pestroutes domain
    # create customer with first name, last name, and address
    When I create customer with "Interview", "Test", and "7575 frankford road, 75252"
    Then I validate if customer name and address match in overview tab
