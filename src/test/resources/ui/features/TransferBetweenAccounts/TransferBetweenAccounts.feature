Feature: Digital Bank Transfer Between Accounts Page

  Background:
    Given User created account with following fields with mocked email and ssn:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |
    And created following two checking accounts with the following details:
      | checkingAccountType | accountOwnership | accountName       | initialDepositAmount |
      | Standard Checking   | Individual       | Account1 Checking | 1000.00              |
      | Standard Checking   | Individual       | Account2 Checking | 500.00               |

  Scenario: Positive case, As a user, i want to successfully transfer money from one checking to another account
    When the user makes a transfer from "Account1 Checking (Standard Checking)" to "Account2 Checking (Standard Checking)" of "500.00"
    Then the user should be able to successfully make the transfer and see the transaction:
      | date       | category | description                   | amount | balance |
      | 2024-03-19 | Income   | (TRN) - Transfer from Account | 500.00 | 1000.00 |


  Scenario Outline: Negative case, As a user, i want to successfully transfer money from savings one to another account
    When the user makes a transfer from "<account1>" to "<account2>" of "<amount>"
    Then the user should see error mesage "<errorMessage>" in the "<field>"

    Examples:
      | account1                              | account2                              | amount   | errorMessage                                                                                      | field        |
      | Account1 Checking (Standard Checking) | Account2 Checking (Standard Checking) |          | Please fill out this field.                                                                       | amount       |
      | Account1 Checking (Standard Checking) |                                       | 500.00   | Please select an item in the list.                                                                | account2     |
      |                                       | Account2 Checking (Standard Checking) | 500.00   | Please select an item in the list.                                                                | account1     |
      | Account1 Checking (Standard Checking) | Account2 Checking (Standard Checking) | dsvss    | Please match the requested format.                                                                | amount       |
      | Account1 Checking (Standard Checking) | Account2 Checking (Standard Checking) | -500.00  | Please match the requested format.                                                                | amount       |
      | Account1 Checking (Standard Checking) | Account2 Checking (Standard Checking) | 5@0#0.00 | Please match the requested format.                                                                | amount       |
      | Account1 Checking (Standard Checking) | Account2 Checking (Standard Checking) | 2000.00  | Error The amount ($2000.00) requested for transfer is more than the available balance ($1000.00). | alertMessage |






