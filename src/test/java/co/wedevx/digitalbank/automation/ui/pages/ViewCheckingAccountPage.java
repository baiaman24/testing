package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BasePageMenu{

    public ViewCheckingAccountPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(id = "new-account-conf-alert")
    private WebElement successfulConfirmationAlert;

    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;

    @FindBy(xpath = "//table[@id = \"transactionTable\"]/tbody/tr/td")
    private List<WebElement> actualData;

    public Map<String, String> getTransactionTableData(){

        Map<String, String> actualTableInfo = new HashMap<>();
        actualTableInfo.put("date", actualData.get(0).getText().substring(0,actualData.get(0).getText().indexOf(" ")));
        actualTableInfo.put("category", actualData.get(1).getText());
        actualTableInfo.put("description", actualData.get(2).getText().substring(actualData.get(0).getText().indexOf(" ")));
        actualTableInfo.put("amount", actualData.get(3).getText().substring(1));
        actualTableInfo.put("balance", actualData.get(4).getText().substring(1));

        return actualTableInfo;
    }

    public Map<String, String> getNewlyAddedAccountInfoMap() {

        WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size() - 1);
        String actualResult = lastAccountCard.getText();

        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("accountName", actualResult.substring(0, actualResult.indexOf("\n")));
        actualResultMap.put("ownership", actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("accountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate")).trim());
        actualResultMap.put("interestRate", actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance:")).trim());
        actualResultMap.put("balance", actualResult.substring(actualResult.indexOf("Balance:") + 10, actualResult.length()).trim());
        actualResultMap.put("accountType", actualResult.substring(actualResult.indexOf("Account:"), actualResult.indexOf("Ownership:")).trim());

        return actualResultMap;


    }

    public String getActualConfirmationMessage() {
        return successfulConfirmationAlert.getText().substring(0, successfulConfirmationAlert.getText().indexOf("\n"));
    }
}
