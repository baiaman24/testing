package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.ChromiumDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {

            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "saucelabs":
                    String platform = ConfigReader.getPropertiesValue("dbank.saucelabs.platform");
                    String saucelabBrowser = ConfigReader.getPropertiesValue("dbank.saucelabs.browser");
                    String browserVersion = ConfigReader.getPropertiesValue("dbank.saucelabs.browser.version");
                    driver = loadSauceLabs(platform, saucelabBrowser, browserVersion);
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromiumDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

            }
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            //take screeschot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "screenshot");

            saveScreenshotToFile(screenshot);

        }
    }

    public static void saveScreenshotToFile(final byte[] screenshot) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String timeStamp = dateFormat.format(new Date());
        String fileName = "C:\\Users\\Lenovo\\Desktop\\амазон\\Screenshots" + File.separator + "screeshot_" + timeStamp + ".png";
        Path filePath = Paths.get(fileName);
        try {
            Files.write(filePath, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browserType, String browserVersion) {
        String USERNAME = ConfigReader.getPropertiesValue("dbank.saucelabs.username");
        String ACCESS_KEY = "cc35dff0-e3f7-4c92-a086-eb8b89927cae";
        String HOST = ConfigReader.getPropertiesValue("dbank.saucelabs.host");

        //setup url to the hub which is running  on sauceLabs vms
        String url = "https://" + USERNAME + ":" + ACCESS_KEY + "@" + HOST;


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browserType);
        capabilities.setCapability("browserVersion", browserVersion);


        //what is ENUM=predefined and limited amount of options, saves me form mistyping
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return driver;
    }
}
