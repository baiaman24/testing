@Deposit @Checking
Feature:  Digital Bank Deposit Page

  Background:
    Given the user created a new account with the following fields:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 |true            |
    And created checking account with the following info:
      | checkingAccountType | accountOwnership | accountName                       | initialDepositAmount |
      | Standard Checking   | Individual       | Cristiano Ronaldo Second Checking | 1000.00              |


  Scenario: Positive case, As a user, i want to successfully deposit money to my Checking Account

    When the user deposits "500.00" to "Cristiano Ronaldo Second Checking (Standard Checking)"
    Then the user should be able to succesfully make a deposit and see the following info on View Checking page:
      | date       | category | description            | amount | balance |
      | 2024-03-19 | Income   | (DPT) - Online Deposit | 500.00 | 1500.00 |

  Scenario: Negative case, user deposits money without specifying receiving account
    When the user deposits "500.00" without selecting receiving account
    Then the user should see the following message on the list of account "Please select an item in the list."

  Scenario: Negative case, user makes a deposit without specifying amount to be deposited
    When the user makes a deposit to "Cristiano Ronaldo Second Checking (Standard Checking)" without specifying amount to be deposited
    Then the user should see the following message on the amount textbox "Please fill out this field."


  Scenario Outline: Negative case, user makes deposit specifying invalid amount
    When the user makes an invalid deposit of "<invalidAmount>" to "Cristiano Ronaldo Second Checking (Standard Checking)" account
    Then the user should see an error message "Please match the requested format"

    Examples:
      | invalidAmount |
      | -500          |
      | sdkjfb        |
      | 9$5%          |
