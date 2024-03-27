package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateSavingsAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.DepositPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SavingsDepositSteps {

    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    DepositPage depositPage = new DepositPage(getDriver());
    CreateSavingsAccountPage createSavingsAccountPage = new CreateSavingsAccountPage(getDriver());
    @Given("the user created a new account on db with the following fields:")
    public void the_user_created_a_new_account_on_db_with_the_following_fields(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        registrationPage.enterAllInfo(registrationDataMap);
        registrationPage.signIn(registrationDataMap.get(0).get("password"));
    }
    @Given("created savings account with the following info:")
    public void created_savings_account_with_the_following_info(List<Map<String, String>> savingsAccountInfoList) {
        Map<String, String> testData = savingsAccountInfoList.get(0);

        createSavingsAccountPage.createAccount(testData.get("accountName"), String.valueOf(testData.get("initialDepositAmount")),
                testData.get("savingsAccountType"), testData.get("accountOwnership"));

    }
    @When("the user deposits {string} to {string} saving account")
    public void the_user_deposits_to_saving_account(String amount, String accountName) {

        depositPage.makeDeposit(accountName, amount);

    }
    @Then("the user should be able to succesfully make a deposit and see the following info on View Saving page:")
    public void the_user_should_be_able_to_succesfully_make_a_deposit_and_see_the_following_info_on_view_saving_page(List<BankTransaction> bankTransactionList) {

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


    @When("the user intends to deposit {string} to {string} savings account and resets the page")
    public void the_user_intends_to_deposit_to_savings_account_and_resets_the_page(String amount, String accountName) {

        depositPage.fillTheAreas(accountName, amount);

        assertFalse(depositPage.checkReset());

        depositPage.reset();

    }
    @Then("all the selected and filled fields should be empty")
    public void all_the_selected_and_filled_fields_should_be_empty() {
        assertTrue(depositPage.checkReset());
    }
}
