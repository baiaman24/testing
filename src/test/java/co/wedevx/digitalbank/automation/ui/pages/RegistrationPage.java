package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;


public class RegistrationPage extends BasePage{
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    private WebElement titleDropdown;

    @FindBy(id = "firstName")
    private WebElement firstNameTxtBox;

    @FindBy(id = "lastName")
    private WebElement lastNameTxtBox;
    @FindBy(xpath = "//input[@value = \"M\"]")
    private WebElement maleRadioBtn;

    @FindBy(xpath = "//input[@value = \"F\"]")
    private WebElement femaleRadioBtn;

    @FindBy(id = "dob")
    private WebElement dateOfBirthTxtbox;

    @FindBy(id = "ssn")
    private WebElement socialSecNumTxtBox;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "confirmPassword")
    private WebElement confirmTxtBox;

    @FindBy(xpath = "//button [@type = \"submit\"]")
    private WebElement nextBtn;

    @FindBy(id = "address")
    private WebElement addressTxtBox;

    @FindBy(id = "locality")
    private WebElement localityTxtBox;

    @FindBy(id = "region")
    private WebElement regionTxtBox;

    @FindBy(id = "postalCode")
    private WebElement postalCodeTxtBox;

    @FindBy(id = "country")
    private WebElement countryTxtBox;

    @FindBy(id = "homePhone")
    private WebElement homephoneTxtBox;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxtBox;

    @FindBy(id = "workPhone")
    private WebElement workPhoneTxtBox;

    @FindBy(id = "agree-terms")
    private WebElement agreeTermsRadioBtn;

    @FindBy(xpath = "//button")
    private WebElement registerBtn;

    @FindBy(xpath = "//div[@class = 'sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement message;

    @FindBy(id = "submit")
    private WebElement signInBtn;


    public void enterAllInfo(List<Map<String, String>> registrationDataMap) {
        Map<String, String> firstRow = registrationDataMap.get(0);
        Select titleSelect = new Select(titleDropdown);

        if (firstRow.get("title") != null) {
            titleSelect.selectByVisibleText(firstRow.get("title"));
        }
        if (firstRow.get("firstName") != null) {
            firstNameTxtBox.sendKeys(firstRow.get("firstName"));
        }

        if (firstRow.get("lastName") != null) {
            lastNameTxtBox.sendKeys(firstRow.get("lastName"));
        }

        if (firstRow.get("gender") != null) {
            switch (firstRow.get("gender")) {
                case "F":
                case "f":
                    femaleRadioBtn.click();
                    break;
                case "M":
                case "m":
                    maleRadioBtn.click();
                    break;
                default:
                    System.out.println("Invalid gender");
            }
        }

        if (firstRow.get("dob") != null) {
            dateOfBirthTxtbox.sendKeys(firstRow.get("dob"));
        }

        MockData mockData = new MockData();
        Map<String, String> fakeData = mockData.generateRandomUser();

        if (firstRow.get("ssn") != null) {
            socialSecNumTxtBox.sendKeys(firstRow.get("ssn"));
        }

        if (firstRow.get("email") != null) {
            emailAddressTxtBox.sendKeys(firstRow.get("email"));
        }

        if (firstRow.get("password") != null) {
            passwordTxtBox.sendKeys(firstRow.get("password"));
            confirmTxtBox.sendKeys(firstRow.get("password"));
        }
        nextBtn.click();

        if(addressTxtBox.isDisplayed()) {

            if (firstRow.get("address") != null) {
                addressTxtBox.sendKeys(firstRow.get("address"));
            }

            if (firstRow.get("locality") != null) {
                localityTxtBox.sendKeys(firstRow.get("locality"));
            }

            if (firstRow.get("region") != null) {
                regionTxtBox.sendKeys(firstRow.get("region"));
            }

            if (firstRow.get("postalCode") != null) {
                postalCodeTxtBox.sendKeys(firstRow.get("postalCode"));
            }

            if (firstRow.get("country") != null) {
                countryTxtBox.sendKeys(firstRow.get("country"));
            }

            if (firstRow.get("homePhone") != null) {
                homephoneTxtBox.sendKeys(firstRow.get("homePhone"));
            }
            if (firstRow.get("mobilePhone") != null) {
                mobilePhoneTxtBox.sendKeys(firstRow.get("mobilePhone"));
            }

            if (firstRow.get("workPhone") != null) {
                workPhoneTxtBox.sendKeys(firstRow.get("workPhone"));
            }

            if (firstRow.get("termsCheckMark") != null) {
                if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsRadioBtn.click();
                }
            }
            registerBtn.click();
        }

    }

    public String getMessage() {
        return message.getText().substring(0, message.getText().lastIndexOf(".") + 1);
    }

    public void signIn(String password) {
        passwordTxtBox.sendKeys(password);
        signInBtn.click();
    }

    public String getRequiredFieldErrorMessage (String fieldName){
        switch (fieldName.toLowerCase()){
            case "title":
                return titleDropdown.getAttribute("validationMessage");
            case "firstname":
                return firstNameTxtBox.getAttribute("validationMessage");
            case "lastname":
                return lastNameTxtBox.getAttribute("validationMessage");
            case "gender":
                return maleRadioBtn.getAttribute("validationMessage");
            case "dob":
                return dateOfBirthTxtbox.getAttribute("validationMessage");
            case "ssn":
                return socialSecNumTxtBox.getAttribute("validationMessage");
            case "email":
                return emailAddressTxtBox.getAttribute("validationMessage");
            case "password":
                return passwordTxtBox.getAttribute("validationMessage");
            case "address":
                return addressTxtBox.getAttribute("validationMessage");
            case "locality":
                return localityTxtBox.getAttribute("validationMessage");
            case "region":
                return regionTxtBox.getAttribute("validationMessage");
            case "postalcode":
                return postalCodeTxtBox.getAttribute("validationMessage");
            case "country":
                return countryTxtBox.getAttribute("validationMessage");
            case "homephone":
                return homephoneTxtBox.getAttribute("validationMessage");
            case "mobilephone":
                return mobilePhoneTxtBox.getAttribute("validationMessage");
            case "workphone":
                return workPhoneTxtBox.getAttribute("validationMessage");


        }
        return "wrong";
    }

}
