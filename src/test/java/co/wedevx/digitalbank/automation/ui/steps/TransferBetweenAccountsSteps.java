package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.pages.TransferBetweenAccountsPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferBetweenAccountsSteps {
    private RegistrationPage registrationPage = new RegistrationPage(getDriver());
    private CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage(getDriver());
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(getDriver());
    TransferBetweenAccountsPage transferBetweenAccountsPage = new TransferBetweenAccountsPage(getDriver());
    @Given("User created account with following fields with mocked email and ssn:")
    public void user_created_account_with_following_fields_with_mocked_email_and_ssn(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        registrationPage.enterAllInfo(registrationDataMap);
        registrationPage.signIn(registrationDataMap.get(0).get("password"));

    }
    @Given("created following two checking accounts with the following details:")
    public void created_following_two_checking_accounts_with_the_following_details(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testData1 = checkingAccountInfoList.get(0);
        NewCheckingAccountInfo testData2 = checkingAccountInfoList.get(1);

        createCheckingAccountPage.createAccount(testData1.getAccountName(), String.valueOf(testData1.getInitialDepositAmount()),
                testData1.getCheckingAccountType(), testData1.getAccountOwnership());

        createCheckingAccountPage.createAccount(testData2.getAccountName(), String.valueOf(testData2.getInitialDepositAmount()),
                testData2.getCheckingAccountType(), testData2.getAccountOwnership());


    }
    @When("the user makes a transfer from {string} to {string} of {string}")
    public void the_user_makes_a_transfer_from_to_of(String fromAccount, String toAccount, String amount) {
        transferBetweenAccountsPage.makeTransfer(fromAccount, toAccount, amount);
    }
    @Then("the user should be able to successfully make the transfer and see the transaction:")
    public void the_user_should_be_able_to_successfully_make_the_transfer_and_see_the_transaction(List<BankTransaction> bankTransactionList) {
        BankTransaction testData = bankTransactionList.get(0);
        Map<String, String> actualData = viewCheckingAccountPage.getTransactionTableData();

        String actualDescription = actualData.get("description").substring(0, actualData.get("description").lastIndexOf(" "));
        assertEquals(testData.getDate(), actualData.get("date"),"Transfer Date mismatch");
        assertEquals(testData.getCategory(), actualData.get("category"), "Transfer Category mismatch");
        assertEquals(testData.getDescription(), actualDescription, "Transfer Description mismatch");
        assertEquals(testData.getAmount(), Double.parseDouble(actualData.get("amount")), "Transfer Amount mismatch");
        assertEquals(testData.getBalance(), Double.parseDouble(actualData.get("balance")), "Transfer Balance mismatch");
    }

    @Then("the user should see error mesage {string} in the {string}")
    public void the_user_should_see_error_mesage_in_the(String errorMessage, String field) {

        String actualMessage = transferBetweenAccountsPage.getTheErrorMessage(field);
        assertEquals(errorMessage, actualMessage, "Error message mismatch");
    }


}
