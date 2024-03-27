package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewExternalAccPage extends BasePageMenu{
    public ViewExternalAccPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//button[@class= \"btn btn-primary\"]")
    private WebElement alertAcceptanceBtn;

    @FindBy(id ="largeModalLabel" )
    private WebElement alertMessage;

    public String getErrorMessage(){
        return alertMessage.getText();
    }
    public void acceptAlert(){
        alertAcceptanceBtn.click();
    }
}
