package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class DepositPage extends BasePageMenu {

    public DepositPage(WebDriver driver){
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

    public void makeDeposit(String accountName, String amount){
        depositPageBtn.click();
        Select accountSelect = new Select(selectAccount);
        accountSelect.selectByVisibleText(accountName);
        amountTxtBox.sendKeys(amount);
        submitBtn.click();
    }
    public void makeDepositWithoutAmount(String accountName){
        depositPageBtn.click();
        Select accountSelect = new Select(selectAccount);
        accountSelect.selectByVisibleText(accountName);
        submitBtn.click();
    }

    public void makeDepositWithoutAccName(String amount){
        depositPageBtn.click();
        amountTxtBox.sendKeys(amount);
        submitBtn.click();
    }

    public boolean checkReset(){
        if(!selectAccount.isSelected() && amountTxtBox.getAttribute("value").equals("")){
            return true;
        }
        return false;
    }
    public void fillTheAreas(String accountName, String amount){
        depositPageBtn.click();
        Select accountSelect = new Select(selectAccount);
        accountSelect.selectByVisibleText(accountName);
        amountTxtBox.sendKeys(amount);

    }
    public void reset(){
        resetBtn.click();
    }


}
