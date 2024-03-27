package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.CreateSavingsAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.pages.VisaDirectTransferPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisaDirectPaymentSteps {
    private RegistrationPage registrationPage = new RegistrationPage(getDriver());
    private CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage(getDriver());
    private CreateSavingsAccountPage createSavingsAccountPage = new CreateSavingsAccountPage(getDriver());
    private VisaDirectTransferPage visaDirectTransferPage = new VisaDirectTransferPage(getDriver());

    @Given("the user created a new account with the following data and mocked ssn and email:")
    public void the_user_created_a_new_account_with_the_following_data_and_mocked_ssn_and_email(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        registrationPage.enterAllInfo(registrationDataMap);
        registrationPage.signIn(registrationDataMap.get(0).get("password"));
    }
    @Given("the user created a new checking account with the following info:")
    public void the_user_created_a_new_checking_account_with_the_following_info(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testData = checkingAccountInfoList.get(0);

        createCheckingAccountPage.createAccount(testData.getAccountName(), String.valueOf(testData.getInitialDepositAmount()),
                testData.getCheckingAccountType(), testData.getAccountOwnership());
    }
    @When("the user makes visa payment choosing {string} account in the list and entering valid {string} amount")
    public void the_user_makes_visa_payment_choosing_account_in_the_list_and_entering_valid_amount(String accNum, String amount) {
        visaDirectTransferPage.makeTransferToVisa(accNum, amount);
    }

    @Then("the user should see {string} error message in the {string} field")
    public void the_user_should_see_error_message_in_the_field(String expectedMessage, String field) {
        assertEquals(expectedMessage, visaDirectTransferPage.getErrorMessage(field));
    }

    @Given("the user created a new savings account with the following info:")
    public void the_user_created_a_new_savings_account_with_the_following_info(List<Map<String, String>> savingsAccountData) {
        Map<String, String> expectedData = savingsAccountData.get(0);
        createSavingsAccountPage.createAccount(expectedData.get("accountName"), expectedData.get("initialDepositAmount"),
                expectedData.get("savingsAccountType"), expectedData.get("accountOwnership"));
    }
}
