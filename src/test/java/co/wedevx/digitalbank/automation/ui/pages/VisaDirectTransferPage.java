package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class VisaDirectTransferPage extends BasePageMenu{
    public VisaDirectTransferPage(WebDriver driver){
        super(driver);
    }
    @FindBy(id = "extAccount")
    private WebElement visaAccountNumList;

    @FindBy(id = "extAmount")
    private  WebElement amountTxtBox;

    @FindBy(xpath = "//button[@class= \"btn btn-primary btn-sm\"]")
    private  WebElement submitBtn;

    @FindBy(xpath = "//div[@class= \"sufee-alert alert with-close alert-danger alert-dismissible fade show\"]")
    private  WebElement alertMessage;

    public void makeTransferToVisa(String accNum, String amount){
        visaDirectTransferBtn.click();
        Select visaAccountSelect = new Select(visaAccountNumList);

        if(accNum.equalsIgnoreCase("1") || accNum.equalsIgnoreCase("2")){
            visaAccountSelect.selectByIndex(Integer.parseInt(accNum));
        }

        if(!amount.isEmpty()){
            amountTxtBox.sendKeys(amount);
        }

        submitBtn.click();
    }

    public String getErrorMessage(String field){
        if(field.equalsIgnoreCase("accountNum")){
            return visaAccountNumList.getText();
        }else if(field.equalsIgnoreCase("amount")){
            return amountTxtBox.getText();
        } else if (field.equalsIgnoreCase("alertMessage")) {
            return alertMessage.getText().substring(0, alertMessage.getText().lastIndexOf(".")+1);

        }
        return "Field not found";
    }
}
