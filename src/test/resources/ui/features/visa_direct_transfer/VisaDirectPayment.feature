@broken
Feature:

  Background:
    Given the user created a new account with the following data and mocked ssn and email:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |

  Scenario:
    Given the user created a new checking account with the following info:
      | checkingAccountType | accountOwnership | accountName                       | initialDepositAmount |
      | Standard Checking   | Individual       | Cristiano Ronaldo Second Checking | 1000.00              |
    When the user makes visa payment choosing "1" account in the list and entering valid "500.00" amount
    Then the user should see "Service Unavailable The VISA API service is temporarily unavailable. We applogize for any inconvenience this may cause. Please try again at a later time." error message in the "alertMessage" field

  Scenario:
    Given the user created a new savings account with the following info:
      | savingsAccountType | accountOwnership | accountName                       | initialDepositAmount |
      | Savings            | Individual       | Cristiano Ronaldo Second Checking | 1000.00              |
    When the user makes visa payment choosing "2" account in the list and entering valid "500.00" amount
    Then the user should see "Service Unavailable The VISA API service is temporarily unavailable. We applogize for any inconvenience this may cause. Please try again at a later time." error message in the "alertMessage" field




