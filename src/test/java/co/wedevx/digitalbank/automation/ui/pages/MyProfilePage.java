package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends BasePageMenu{
    private WebDriver driver;

    public MyProfilePage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//select[@id = \"title\"]/option[1]")
    WebElement pleaseSelectTitleDropdown;

    @FindBy(xpath = "//select[@id = \"title\"]/option[2]")
    WebElement mrTitleDropdown;

    @FindBy (xpath = "//select[@id = \"title\"]/option[3]")
    WebElement msTitleDropdown;

    @FindBy (xpath = "//select[@id = \"title\"]/option[4]")
    WebElement mrsTitleDropdown;

    @FindBy (id = "firstName")
    WebElement firstNameTxtBox;

    @FindBy(id = "lastName")
    WebElement lastNameTxtBox;

    @FindBy(id = "homePhone")
    WebElement homePhoneTxtBox;

    @FindBy( id = "mobilePhone")
    WebElement mobilePhoneTxtBox;

    @FindBy(id = "workPhone")
    WebElement workPhoneTxtBox;

    @FindBy(id = "address")
    WebElement addressTxtBox;

    @FindBy (id ="locality")
    WebElement localityTxtBox;

    @FindBy (id = "region")
    WebElement regionTxtBox;

    @FindBy (id = "postalCode")
    WebElement postalCodeTxtBox;

    @FindBy(id = "country")
    WebElement countryTxtBox;

    @FindBy(xpath = "//button[@class = \"btn btn-primary btn-sm\"]")
    WebElement submitBtn;

    @FindBy (xpath = "//button[@class = \"btn btn-danger btn-sm\"]")
    WebElement resetBtn;

    public void chooseMrTitle(){
        pleaseSelectTitleDropdown.click();
        mrTitleDropdown.click();
    }

    public  void chooseMsTitle(){
        pleaseSelectTitleDropdown.click();
        msTitleDropdown.click();
    }

    public void chooseMrsTitle(){
        pleaseSelectTitleDropdown.click();
        mrsTitleDropdown.click();
    }

    public void enterFullName(String firstName, String lastName){
        firstNameTxtBox.sendKeys(firstName);
        lastNameTxtBox.sendKeys(lastName);
    }

    public void enterFullAddress(String address, String locality, String region, String postalCode, String country){
        addressTxtBox.sendKeys(address);
        localityTxtBox.sendKeys(locality);
        regionTxtBox.sendKeys(region);
        postalCodeTxtBox.sendKeys(postalCode);
        countryTxtBox.sendKeys(country);
    }

    public void enterNumbers(String homePhone, String mobilePhone, String workPhone){
        homePhoneTxtBox.sendKeys(homePhone);
        mobilePhoneTxtBox.sendKeys(mobilePhone);
        workPhoneTxtBox.sendKeys(workPhone);
    }

    public void submitInfo(){
        submitBtn.click();
    }

    public void resetInfo(){
        resetBtn.click();
    }

}
