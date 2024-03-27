package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.pages.CreateSavingsAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.pages.WithdrawPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsWithdrawSteps {
    private RegistrationPage registrationPage = new RegistrationPage(getDriver());
    private CreateSavingsAccountPage createSavingsAccountPage = new CreateSavingsAccountPage(getDriver());
    private WithdrawPage withdrawPage = new WithdrawPage(getDriver());

    @Given("the user created a new account on digitalbank with the following fields:")
    public void the_user_created_a_new_account_on_digitalbank_with_the_following_fields(List<Map<String, String>> registrationDataMap) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpage"));
        registrationPage.enterAllInfo(registrationDataMap);
        registrationPage.signIn(registrationDataMap.get(0).get("password"));

    }
    @Given("created savings account with the following details:")
    public void created_savings_account_with_the_following_details(List<Map<String, String>> savingsAccountInfoList) {
        Map<String, String> testData = savingsAccountInfoList.get(0);
        createSavingsAccountPage.createAccount(testData.get("accountName"), String.valueOf(testData.get("initialDepositAmount")),
                testData.get("savingsAccountType"), testData.get("accountOwnership"));


    }
    @When("the user withdraws {string} from {string} saving account")
    public void the_user_withdraws_from_saving_account(String amount, String accName) {
        withdrawPage.withdraw(accName, amount);

    }
    @Then("the user should be able to succesfully make a withdrawal and see the following info on View Saving page:")
    public void the_user_should_be_able_to_succesfully_make_a_withdrawal_and_see_the_following_info_on_view_saving_page(List<BankTransaction> bankTransactionList) {
        BankTransaction testData = bankTransactionList.get(0);

        List<WebElement> transactionTable = getDriver().findElements(By.xpath("//table[@id = \"transactionTable\"]/tbody/tr[1]/td"));

        String actualDate = transactionTable.get(0).getText().substring(0, transactionTable.get(0).getText().indexOf(" "));
        String actualCategory = transactionTable.get(1).getText();
        String actualDescription = transactionTable.get(2).getText().substring(transactionTable.get(2).getText().indexOf(" ") + 1);
        Double actualAmount = Double.parseDouble(transactionTable.get(3).getText().substring(1));
        Double actualBalance = Double.parseDouble(transactionTable.get(4).getText().substring(1));

        assertEquals(testData.getDate(), actualDate,"Withdraw Date mismatch");
        assertEquals(testData.getCategory(), actualCategory, "Withdraw Category mismatch");
        assertEquals(testData.getDescription(), actualDescription, "Withdraw Description mismatch");
        assertEquals(testData.getAmount(), actualAmount, "Withdraw Amount mismatch");
        assertEquals(testData.getBalance(), actualBalance, "Withdraw Balance mismatch");
    }

    @Then("the user should see the following error message {string} in the following field {string}:")
    public void the_user_should_see_the_following_error_message_in_the_following_field(String errorMessage, String field) {
        assertEquals(errorMessage, withdrawPage.getErrorMessage(field));
    }
}
