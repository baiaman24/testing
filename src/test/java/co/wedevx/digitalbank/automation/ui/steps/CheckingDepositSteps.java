package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.DepositPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
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

public class CheckingDepositSteps {
    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    DepositPage depositPage = new DepositPage(getDriver());
    CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage(getDriver());

    @Given("the user created a new account with the following fields:")
    public void the_user_created_a_new_account_with_the_following_fields(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));

    }

    @Given("created checking account with the following info:")
    public void created_checking_account_with_the_following_info(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testData = checkingAccountInfoList.get(0);

        createCheckingAccountPage.createAccount(testData.getAccountName(), String.valueOf(testData.getInitialDepositAmount()),
                testData.getCheckingAccountType(), testData.getAccountOwnership());
    }

    @When("the user deposits {string} to {string}")
    public void the_user_deposits_to(String amount, String accountName) {

        depositPage.makeDeposit(accountName, amount);

    }

    @Then("the user should be able to succesfully make a deposit and see the following info on View Checking page:")
    public void the_user_should_be_able_to_succesfully_make_a_deposit_and_see_the_following_info_on_view_checking_page(List<BankTransaction> bankTransactionList) {
        BankTransaction testData = bankTransactionList.get(0);

        List<WebElement> transactionTable = getDriver().findElements(By.xpath("//table[@id = \"transactionTable\"]/tbody/tr[1]/td"));

        String actualDate = transactionTable.get(0).getText().substring(0, transactionTable.get(0).getText().indexOf(" "));
        String actualCategory = transactionTable.get(1).getText();
        String actualDescription = transactionTable.get(2).getText().substring(transactionTable.get(2).getText().indexOf(" ") + 1);
        Double actualAmount = Double.parseDouble(transactionTable.get(3).getText().substring(1));
        Double actualBalance = Double.parseDouble(transactionTable.get(4).getText().substring(1));

        assertEquals(testData.getDate(), actualDate,"Deposit Date mismatch");
        assertEquals(testData.getCategory(), actualCategory, "Deposit Category mismatch");
        assertEquals(testData.getDescription(), actualDescription, "Deposit Description mismatch");
        assertEquals(testData.getAmount(), actualAmount, "Deposit Amount mismatch");
        assertEquals(testData.getBalance(), actualBalance, "Deposit Balance mismatch");
    }

    @When("the user deposits {string} without selecting receiving account")
    public void the_user_deposits_without_selecting_receiving_account(String amount) {
        depositPage.makeDepositWithoutAccName(amount);
    }

    @Then("the user should see the following message on the list of account {string}")
    public void the_user_should_see_the_following_message_on_the_list_of_account(String expectedErrorMessage) {

    }


    @When("the user makes a deposit to {string} without specifying amount to be deposited")
    public void the_user_makes_a_deposit_to_without_specifying_amount_to_be_deposited(String accName) {

        depositPage.makeDepositWithoutAmount(accName);

    }

    @Then("the user should see the following message on the amount textbox {string}")
    public void the_user_should_see_the_following_message_on_the_amount_textbox(String expectedErrorMessage) {


    }

    @When("the user makes an invalid deposit of {string} to {string} account")
    public void the_user_makes_an_invalid_deposit_of_to_account(String invalidAmount, String accName) {
        depositPage.makeDeposit(accName, invalidAmount);
    }


    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String errorMessage) {

    }

}
