package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LinkExternalAccountsPage extends BasePageMenu{

    public LinkExternalAccountsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@class=\"sufee-alert alert with-close alert-danger alert-dismissible fade show\"]")
    private WebElement alertMessage;

    @FindBy(id = "bankId")
    private WebElement bankingProvidersListBtn;

    @FindBy(id = "username")
    private  WebElement usernameTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(xpath = "//button[@class=\"btn btn-primary btn-sm\"]")
    private WebElement submitBtn;

    @FindBy (xpath = "//button[@class=\"btn btn-danger btn-sm\"]")
    private WebElement resetBtn;

    public boolean areLinkExternalPageElementsFilled(){
        externalAccountsMenuDropdown.click();
        linkExternalAccBtn.click();
        Select bankProvidersSelect = new Select(bankingProvidersListBtn);
        List<WebElement> providersList = bankProvidersSelect.getOptions();
        System.out.println(providersList.size());
        System.out.println(usernameTxtBox.getText());
        System.out.println(passwordTxtBox.getText());
        if(providersList.size()==1 && !usernameTxtBox.getAttribute("value").isEmpty() && !passwordTxtBox.getAttribute("value").isEmpty()){
            return true;
        }
        return false;
    }

    public String getErrorMessage(){
        return alertMessage.getText().substring(0, alertMessage.getText().lastIndexOf("."));
    }
}
