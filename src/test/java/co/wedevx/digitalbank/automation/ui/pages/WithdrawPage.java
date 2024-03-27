package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class WithdrawPage extends BasePageMenu {

    public WithdrawPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "selectedAccount")
    private WebElement selectAccount;

    @FindBy(id = "amount")
    private WebElement amountTxtBox;

    @FindBy(xpath = "//button[@class=\"btn btn-primary btn-sm\"]")
    private  WebElement submitBtn;

    @FindBy(xpath = "//button[@class=\"btn btn-danger btn-sm\"]")
    private  WebElement resetBtn;

    @FindBy(xpath = "//div[@class=\"sufee-alert alert with-close alert-danger alert-dismissible fade show\"]")
    private  WebElement alertMessage;

    public void withdraw(String accountName, String amount){
        withdrawPageBtn.click();
        if(!accountName.isEmpty()) {
            Select accountSelect = new Select(selectAccount);
            accountSelect.selectByVisibleText(accountName);
        }
        if(!amount.isEmpty()) {
            amountTxtBox.sendKeys(amount);
        }
        submitBtn.click();
    }

    public boolean checkReset(){
        if(!selectAccount.isSelected() && amountTxtBox.getAttribute("value").equals("")){
            return true;
        }
        return false;
    }
    public void fillTheAreas(String accountName, String amount){
        withdrawPageBtn.click();
        Select accountSelect = new Select(selectAccount);
        accountSelect.selectByVisibleText(accountName);
        amountTxtBox.sendKeys(amount);

    }
    public void reset(){
        resetBtn.click();
    }

    public String getErrorMessage(String field){
        if(field.equalsIgnoreCase("alertMessage")){
            return alertMessage.getText().substring(0, alertMessage.getText().lastIndexOf(".") +1);
        }
        else if (field.equalsIgnoreCase("savingsAccount")){
            return selectAccount.getAttribute("validationMessage");
        }
        else if(field.equalsIgnoreCase("withdrawalAmount")){
            return amountTxtBox.getAttribute("validationMessage");
        }
        return "The field is not found";
    }


}

