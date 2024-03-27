@Deposit @Savings
Feature:  Digital Bank Deposit Page

  Background:
    Given the user created a new account on db with the following fields:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |
    And created savings account with the following info:
      | savingsAccountType | accountOwnership | accountName               | initialDepositAmount |
      | Savings            | Individual       | Cristiano Ronaldo Savings | 1000.00              |

  Scenario: Positive case, As a user, i want to successfully deposit money to my Savings Account
    When the user deposits "500.00" to "Cristiano Ronaldo Savings (Savings)" saving account
    Then the user should be able to succesfully make a deposit and see the following info on View Saving page:
      | date       | category | description            | amount | balance |
      | 2024-03-19 | Income   | (DPT) - Online Deposit | 500.00 | 1500.00 |

  Scenario: Positive case, As a user, i want to successfully reset deposit page when intending to deposit money to my Savings Account
    When the user intends to deposit "500.00" to "Cristiano Ronaldo Savings (Savings)" savings account and resets the page
    Then all the selected and filled fields should be empty