package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {

    private LoginPage loginPage;
    private CreateCheckingAccountPage createCheckingAccountPage = new CreateCheckingAccountPage(getDriver());

    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(getDriver());

    @Given("the user logged in as {string} with the password {string}")
    public void the_user_logged_in_as_with_the_password(String username, String password) {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.loginpage"));
        loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);


    }

    @When("the user creates new checking account with the following data:")
    public void the_user_creates_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testData = checkingAccountInfoList.get(0);

        createCheckingAccountPage.createAccount(testData.getAccountName(), String.valueOf(testData.getInitialDepositAmount()),
                testData.getCheckingAccountType(), testData.getAccountOwnership());


    }

    @Then("the user should see the green {string}")
    public void the_user_should_see_the_green(String expectedSuccessfulMessage) {
        expectedSuccessfulMessage = "Confirmation " + expectedSuccessfulMessage;

        assertEquals(expectedSuccessfulMessage,viewCheckingAccountPage.getActualConfirmationMessage());

    }

    @Then("the user should see the following newly added account card:")
    public void the_user_should_see_the_following_newly_added_account_card(List<AccountCard> accountCardList) {
        Map<String, String> actualDataMap = viewCheckingAccountPage.getNewlyAddedAccountInfoMap();
        AccountCard expectedResult = accountCardList.get(0);
        String expectedAccountName = expectedResult.getAccountName().trim();
        String expectedAccountType = expectedResult.getAccountType().trim();
        String expectedOwnership = expectedResult.getOwnership().trim();
        String expectedInterestRate = expectedResult.getInterestRate().trim();
        double expectedBalance = expectedResult.getBalance();


        assertEquals(expectedAccountName, actualDataMap.get("accountName"));
        assertEquals("Account: " + expectedAccountType, actualDataMap.get("accountType"));
        assertEquals("Ownership: " + expectedOwnership, actualDataMap.get("ownership"));

        assertEquals("Interest Rate: " + expectedInterestRate, actualDataMap.get("interestRate"));
        assertEquals(expectedBalance, Double.parseDouble(actualDataMap.get("balance")));


    }

    @Then("the user should see the following transactions:")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedData) {
        Map<String, String> actualData = viewCheckingAccountPage.getTransactionTableData();

        String expectedCategory = expectedData.get(0).getCategory();
        String expectedDescription = expectedData.get(0).getDescription();
        double expectedAmount = expectedData.get(0).getAmount();
        double expectedBalance = expectedData.get(0).getBalance();

        assertEquals(expectedCategory, actualData.get("category"), "Category mismatch");
        assertEquals(expectedAmount, Double.parseDouble(actualData.get("amount")), "Amount mismatch");
        assertEquals(expectedBalance, Double.parseDouble(actualData.get("balance")), "Balance mismatch");

    }

}
