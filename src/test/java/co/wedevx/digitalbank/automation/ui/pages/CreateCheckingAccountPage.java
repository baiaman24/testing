package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingAccountPage extends BasePageMenu{
    private String pageUrl = "https://dbank-qa.wedevx.co/bank/account/checking-add";

    public CreateCheckingAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "name")
    private WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    private WebElement initialDepositTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private  WebElement submitBtn;

    @FindBy (id = "Standard Checking")
    private  WebElement standardCheckingRadioBtn;

    @FindBy (id = "Interest Checking")
    private WebElement interestCheckingRadioBtn;

    @FindBy (id = "Individual")
    private WebElement individualOwnershipRadioBtn;

    @FindBy (id = "Joint")
    private  WebElement jointOwnershipRadioBtn;

    @FindBy (id = "newCheckingReset")
    private WebElement resetBtn;


    public void createAccount(String accountName, String initialDepositAmount, String accountType, String ownershipType){
        checkingMenuBtn.click();
        checkingPageBtn.click();
        assertEquals(pageUrl, getDriver().getCurrentUrl(), "new checking url button did not take to the expected url " );

        if(accountType.equalsIgnoreCase("Standard Checking")){
            standardCheckingRadioBtn.click();
        } else if (accountType.equalsIgnoreCase("Interest Checking")) {
            interestCheckingRadioBtn.click();
        } else{
            throw new NoSuchElementException("No such account type found. Only supports Standard Checking and Interest Checking");
        }

        if(ownershipType.equalsIgnoreCase("Individual")){
            individualOwnershipRadioBtn.click();
        } else if (ownershipType.equalsIgnoreCase("Joint")) {
            jointOwnershipRadioBtn.click();
        } else {
            throw new NoSuchElementException("Only supports individual and joint ownership");
        }

        accountNameTxtBox.sendKeys(accountName);
        initialDepositTxtBox.sendKeys(initialDepositAmount);
        submitBtn.click();

    }

    public void resetInfo(){
        resetBtn.click();
    }




}
