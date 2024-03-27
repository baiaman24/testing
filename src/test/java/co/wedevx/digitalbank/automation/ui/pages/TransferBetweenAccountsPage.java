package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TransferBetweenAccountsPage extends BasePageMenu {

    public TransferBetweenAccountsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "fromAccount")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccount")
    private WebElement toAccountDropdown;

    @FindBy(id = "amount")
    private WebElement amountTxtBox;

    @FindBy(xpath = "//button[@class = \"btn btn-primary btn-sm\"]")
    private WebElement submitBtn;

    @FindBy(xpath = "//button[@class = \"btn btn-danger btn-sm\"]")
    private WebElement resetBtn;

    @FindBy(xpath = "//div[@class= \"sufee-alert alert with-close alert-danger alert-dismissible fade show\"]")
    private WebElement alertMessage;

    public void makeTransfer(String fromAccount, String toAccount, String amount) {
        transferBetweenAccBtn.click();
        if (!fromAccount.isEmpty()) {
            Select fromAccountSelect = new Select(fromAccountDropdown);
            fromAccountSelect.selectByVisibleText(fromAccount);
        }

        if (!toAccount.isEmpty()) {
            Select toAccountSelect = new Select(toAccountDropdown);
            toAccountSelect.selectByVisibleText(toAccount);
        }

        if (!amount.isEmpty()) {
            amountTxtBox.sendKeys(amount);
        }

        submitBtn.click();
    }

    public void reset() {
        resetBtn.click();
    }

    public String getTheErrorMessage(String field) {
        if (field.equalsIgnoreCase("amount")) {
            return amountTxtBox.getAttribute("validationMessage");
        } else if (field.equalsIgnoreCase("account2")) {
            return toAccountDropdown.getAttribute("validationMessage");
        } else if (field.equalsIgnoreCase("account1")) {
            return fromAccountDropdown.getAttribute("validationMessage");
        } else if (field.equalsIgnoreCase("alertMessage")) {
            return alertMessage.getText().substring(0, alertMessage.getText().lastIndexOf(".") + 1);
        }
            return "Field not found";
    }
}
