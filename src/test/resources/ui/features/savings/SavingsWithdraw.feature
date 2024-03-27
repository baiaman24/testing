@Withdraw
Feature: Withdraw functionality for savings account

  Background:
    Given the user created a new account on digitalbank with the following fields:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |
    And created savings account with the following details:
      | savingsAccountType | accountOwnership | accountName               | initialDepositAmount |
      | Savings            | Individual       | Cristiano Ronaldo Savings | 1000.00              |

  Scenario: Positive case, As a User, i want to successfully withdraw money from my savings account
    When the user withdraws "500.00" from "Cristiano Ronaldo Savings (Savings)" saving account
    Then the user should be able to succesfully make a withdrawal and see the following info on View Saving page:
      | date       | category | description              | amount  | balance |
      | 2024-03-19 | Misc     | (WTH) - Online Withdrawl | -500.00 | 500.00  |

  Scenario Outline: Negative case, As a digital bank admin, i want to make sure that when the user intends to withdraw a higher amount than the savings account balance user should not be able to do it
    When the user withdraws "<withdrawalAmount>" from "<savingsAccount>" saving account
    Then the user should see the following error message "<errorMessage>" in the following field "<field>":

    Examples:
      | withdrawalAmount | savingsAccount                      | errorMessage                                                                                                        | field            |
      | 1026.00          | Cristiano Ronaldo Savings (Savings) | Error The withdraw amount ($1026.00) is greater than the available balance ($1000.00) and overdraft limit ($25.00). | alertMessage     |
      | 500.00           |                                     | Please select an item in the list.                                                                                  | savingsAccount   |
      |                  | Cristiano Ronaldo Savings (Savings) | Please fill out this field.                                                                                         | withdrawalAmount |
      | hdshhhdhs        | Cristiano Ronaldo Savings (Savings) | Please match the requested format.                                                                                  | withdrawalAmount |
      | 654@3%           | Cristiano Ronaldo Savings (Savings) | Please match the requested format.                                                                                  | withdrawalAmount |


