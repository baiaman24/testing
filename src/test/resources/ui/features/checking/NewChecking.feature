@newChecking
Feature: Creating Checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "cristiano@gmail.com" with the password "031275Bb$"
    When the user creates new checking account with the following data:
      | checkingAccountType | accountOwnership | accountName                       | initialDepositAmount |
      | Standard Checking   | Individual       | Cristiano Ronaldo Second Checking | 100000.00            |
    Then the user should see the green "Successfully created new Standard Checking account named Cristiano Ronaldo Second Checking"
    And the user should see the following newly added account card:
      | accountName                       | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Cristiano Ronaldo Second Checking | Standard Checking | Individual | 486135252     | 0.0%         | 100000.00 |
    And the user should see the following transactions:
      | date             | category | description               | amount   | balance  |
      | 2024-03-13 01:55 | Income   | 845326674 (DPT) - Deposit | 100000.0 | 100000.0 |