package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.LinkExternalAccountsPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkExternalAccountsSteps {
    private RegistrationPage registrationPage = new RegistrationPage(getDriver());
    private LinkExternalAccountsPage linkExternalAccountsPage = new LinkExternalAccountsPage(getDriver());
    @Given("the user created a new account on digital bank with the following fields:")
    public void the_user_created_a_new_account_on_digital_bank_with_the_following_fields(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        registrationPage.enterAllInfo(registrationDataMap);
        registrationPage.signIn(registrationDataMap.get(0).get("password"));
    }
    @When("the user wants to link an external account")
    public void the_user_wants_to_link_an_external_account() {


    }
    @Then("the user should see an error message {string} and all elements should be filled")
    public void the_user_should_see_an_error_message_and_all_elements_should_be_filled(String expectedMessage) {
        assertTrue(linkExternalAccountsPage.areLinkExternalPageElementsFilled());
        assertEquals(expectedMessage, linkExternalAccountsPage.getErrorMessage());
    }

}
