@broken
Feature: Test broken link external account functionality
  Scenario: As a db admin, when a user wants to link with certain external account, i want to make sure that he will not able to do it
    Given the user created a new account on digital bank with the following fields:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | random | random | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |
    When the user wants to link an external account
    Then the user should see an error message "Error There are no banking providers available." and all elements should be filled