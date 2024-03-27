package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasePageMenu extends BasePage{
    public BasePageMenu(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "checking-menu")
    protected WebElement checkingMenuBtn;
    @FindBy(id = "new-checking-menu-item")
    protected WebElement checkingPageBtn;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingPageBtn;

    @FindBy(id = "savings-menu")
    protected WebElement savingsMenu;

    @FindBy(id = "new-savings-menu-item")
    protected WebElement newSavingsMenuItem;
    @FindBy(id = "view-savings-menu-item")
    protected WebElement viewSavingsPageBtn;

    @FindBy(id = "home-menu-item")
    protected WebElement homeBtn;

    @FindBy(id = "withdraw-menu-item")
    protected WebElement withdrawPageBtn;
    @FindBy(id = "deposit-menu-item")
    protected WebElement depositPageBtn;

    @FindBy(id = "transfer-menu-item")
    protected WebElement transferBetweenAccBtn;

    @FindBy(id = "visa-transfer-menu-item")
    protected WebElement visaDirectTransferBtn;

    @FindBy(id = "external-accounts-menu")
    protected WebElement externalAccountsMenuDropdown;

    @FindBy(id = "link-external-menu-item")
    protected WebElement linkExternalAccBtn;

    @FindBy(id = "view-external-menu-item")
    protected WebElement viewExternalAccBtn;
    public void goToHomePage(){
        homeBtn.click();
    }
    public void goToViewSavings(){
        savingsMenu.click();
        viewSavingsPageBtn.click();
    }

    public void goToViewChecking(){
        checkingMenuBtn.click();
        viewCheckingPageBtn.click();
    }


}
