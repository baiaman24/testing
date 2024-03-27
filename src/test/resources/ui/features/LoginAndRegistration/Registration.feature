@Registration
Feature: Digital Bank Registration Page

  Background:
    Given The following user with the email "jack@test.co" is not in DB
    Given User navigates to Digital Bank signup page

  Scenario: Positive case, As a user, i want to successfully create Digital Bank Account
    When User creates account with following fields:
      | title | firstName | lastName | gender | dob        | ssn         | email        | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 | jack@test.co | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true           |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email        | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExpired|accountNonLocked|credentialsNonExpired|enabled|
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 | jack@test.co | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591888 | 2146591887  | 2146591889 | true             |true            |true                 |true   |

  @NegativeRegistration
  Scenario Outline: Negative test case, As a Digital Bank Admin, i want to make sure users can not register without providing all valid data
    When User creates account with following fields:
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then the User should see the following "<fieldWithError>" required error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob        | ssn         | email        | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | fieldWithError | errorMessage                        | termsCheckMark |
      |       |           |          |        |            |             |              |          |         |          |        |            |         |           |             |           | title          | Please select an item in the list.  |                |
      | Mr.   |           |          |        |            |             |              |          |         |          |        |            |         |           |             |           | firstName      | Please fill out this field.         |                |
      | Mr.   | Jack      |          |        |            |             |              |          |         |          |        |            |         |           |             |           | lastName       | Please fill out this field.         |                |
      | Mr.   | Jack      | Test     |        |            |             |              |          |         |          |        |            |         |           |             |           | gender         | Please select one of these options. |                |
      | Mr.   | Jack      | Test     | M      |            |             |              |          |         |          |        |            |         |           |             |           | dob            | Please fill out this field.         |                |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 |             |              |          |         |          |        |            |         |           |             |           | ssn            | Please fill out this field.         |                |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 |              |          |         |          |        |            |         |           |             |           | email          | Please fill out this field.         |                |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 | jack@test.co |          |         |          |        |            |         |           |             |           | password       | Please fill out this field.         |                |

