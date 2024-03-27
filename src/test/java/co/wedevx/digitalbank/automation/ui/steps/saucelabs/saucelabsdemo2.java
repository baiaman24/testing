package co.wedevx.digitalbank.automation.ui.steps.saucelabs;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class saucelabsdemo2 {
    public static void main(String[] args) throws MalformedURLException {
        //first username and password
        String username = "oauth-baiaman0312-aa3f9";
        String ACCESS_KEY = "cc35dff0-e3f7-4c92-a086-eb8b89927cae";


        //setup url to the hub which is running  on sauceLabs vms
        String url = "https://oauth-baiaman0312-aa3f9:cc35dff0-e3f7-4c92-a086-eb8b89927cae@ondemand.eu-central-1.saucelabs.com:443/wd/hub";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", Platform.MAC);
        capabilities.setCapability("browserName", BrowserType.SAFARI);
        capabilities.setCapability("browserVersion", "latest");


        //what is ENUM=predefined and limited amount of options, saves me form mistyping

        WebDriver driver = new RemoteWebDriver(new URL(url), capabilities);

        driver.get("https://www.amazon.com/");

        WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        searchBox.sendKeys("Iphone" + Keys.ENTER);

        WebElement appleBrand = driver.findElement(By.cssSelector("li[id='p_89/Apple']"));
        System.out.println(appleBrand.getText());

    }
}
