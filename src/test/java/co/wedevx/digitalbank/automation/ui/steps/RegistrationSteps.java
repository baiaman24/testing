package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationSteps {
    RegistrationPage registrationPage = new RegistrationPage(getDriver());

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration page Title mismatch");
    }

    @When("User creates account with following fields:")
    public void user_creates_account_with_following_fields(List<Map<String, String>> registrationDataMap) {
        registrationPage.enterAllInfo(registrationDataMap);

    }

    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedMessage) {

        assertEquals(expectedMessage, registrationPage.getMessage(), "Registration page message mismatch");


    }

    @Then("the User should see the following {string} required error message {string}")
    public void the_user_should_see_the_following_required_error_message(String field, String expectedErrorMessage) {

        String actualErrorMessage = registrationPage.getRequiredFieldErrorMessage(field);
        System.out.println(actualErrorMessage);
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error Message mismatch " + field);
    }

    @Then("the following user should be saved in the db")
    public void theFollowingUserShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileinDBList) {
        Map<String, String> expectedInfoMap = expectedUserProfileinDBList.get(0);
        String queryUserTable = String.format("select * from users where username='%s'", expectedInfoMap.get("email"));
        String queryUserProfileTable = String.format("select * from user_profile where email_address='%s'", expectedInfoMap.get("email"));

        List<Map<String, Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryUserTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfileTable);
        assertEquals(1, actualUserInfoList.size(), "registration generated unexpected number of users");
        assertEquals(1, actualUserProfileInfoList.size(), "registration generated unexpected number of user profiles");

        Map<String, Object> actualUserInfoMap = actualUserInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);

        assertEquals(expectedInfoMap.get("title"), actualUserProfileInfoMap.get("title"), "registration generated wrong title");
        assertEquals(expectedInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "registration generated wrong first name");
        assertEquals(expectedInfoMap.get("lastName"), actualUserProfileInfoMap.get("last_name"), "registration generated wrong last name");
        assertEquals(expectedInfoMap.get("gender"), actualUserProfileInfoMap.get("gender"), "registration generated wrong gender");
        //assertEquals(expectedInfoMap.get("dob"), actualUserProfileInfoMap.get("dob"), "registration generated wrong date of birth");
        assertEquals(expectedInfoMap.get("ssn"), actualUserProfileInfoMap.get("ssn"), "registration generated wrong ssn");
        assertEquals(expectedInfoMap.get("email"), actualUserProfileInfoMap.get("email_address"), "registration generated wrong email");
        assertEquals(expectedInfoMap.get("locality"), actualUserProfileInfoMap.get("locality"), "registration generated wrong locality");
        assertEquals(expectedInfoMap.get("region"), actualUserProfileInfoMap.get("region"), "registration generated wrong region");
        assertEquals(expectedInfoMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "registration generated wrong postalCode");
        assertEquals(expectedInfoMap.get("country"), actualUserProfileInfoMap.get("country"), "registration generated wrong country");
        assertEquals(expectedInfoMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "registration generated wrong home Phone");
        assertEquals(expectedInfoMap.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"), "registration generated wrong mobile Phone");
        assertEquals(expectedInfoMap.get("workPhone"), actualUserProfileInfoMap.get("work_phone"), "registration generated wrong work Phone");


        assertEquals(expectedInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "Account expired");
        assertEquals(expectedInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "Account locked");
        assertEquals(expectedInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "Credentials expired");
        assertEquals(expectedInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "Account disabled");
        assertEquals(expectedInfoMap.get("email"), actualUserInfoMap.get("username"), "Username mismatch");

    }


    @Given("The following user with the email {string} is not in DB")
    public void theFollowingUserWithTheEmailIsNotInDB(String email) {

        String queryForUserProfile = String.format("DELETE from user_profile WHERE email_address='%s'", email);
        String queryForUsers = String.format("DELETE from users WHERE username='%s'", email);
        System.out.println(DBUtils.runSQLUpdateQuery(queryForUserProfile));
        System.out.println(DBUtils.runSQLUpdateQuery(queryForUsers));
    }
}
