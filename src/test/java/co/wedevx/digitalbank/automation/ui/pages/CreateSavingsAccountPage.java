package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSavingsAccountPage extends BasePageMenu{


    private String pageUrl ="https://dbank-qa.wedevx.co/bank/account/savings-add";
    public CreateSavingsAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Savings")
    WebElement savingsSavingsAccTypeRadioBtn;

    @FindBy(id = "Money Market")
    WebElement moneyMarketSavingsAccTypeRadioBtn;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipRadioBtn;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipRadioBtn;

    @FindBy(id = "name")
    private WebElement accountNameTxtBox;
    @FindBy(id = "openingBalance")
    private WebElement initialDepositTxtBox;

    @FindBy(id = "newSavingsSubmit")
    private WebElement submitBtn;

    @FindBy(id = "newSavingsReset")
    private WebElement resetBtn;


    public void createAccount(String accountName, String initialDepositAmount, String accountType, String ownershipType) {
        savingsMenu.click();
        newSavingsMenuItem.click();
        assertEquals(pageUrl, getDriver().getCurrentUrl(), "Create Savings account Webpage does not match");

        if (accountType.equals("Savings")) {
            savingsSavingsAccTypeRadioBtn.click();
        } else if (accountType.equals("Money Market")) {
            moneyMarketSavingsAccTypeRadioBtn.click();
        }

        if (ownershipType.equals("Individual")) {
            individualOwnershipRadioBtn.click();
        } else if (ownershipType.equals("Joint")) {
            jointOwnershipRadioBtn.click();
        }

        accountNameTxtBox.sendKeys(accountName);
        initialDepositTxtBox.sendKeys(initialDepositAmount);
        submitBtn.click();

    }

    public void resetInfo() {
        resetBtn.click();
    }
}
