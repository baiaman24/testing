package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BrowserHelper {

    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;
    }

    public static WebElement fluentWaitForElementPresence(WebDriver driver, By locator, int timeToWaitInSec) {
        WebElement fluentWait = null;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            fluentWait = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Element was not found within the given time" + e.getMessage());
        }
        return fluentWait;

    }

    public static void ScrollIntoView(WebDriver driver, WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        } catch (Exception e) {
            ScrollIntoViewJSExecutor(driver, element);
        }
    }

    public static void ScrollIntoViewJSExecutor(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.err.println("Failed to scroll to the element using JavaScript: " + e.getMessage());
        }

    }

    public static void clickElementWithText(WebDriver driver, String text){
        try {
            WebElement elementToBeClicked = driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
            elementToBeClicked.click();
        } catch (Exception e){
            System.err.println("Element with the following text was not found: " + text + " " + e.getMessage());
        }
    }

    public static  void fillTextInput(WebDriver driver, WebElement element, String text){
        try{
            ScrollIntoView(driver, element);
            if(element.isDisplayed() && element.isEnabled()){
                element.click();
                element.sendKeys(text);
            } else {
                System.err.println("Text input element is not interactable.");
            }
        } catch (Exception e){
            System.err.println("Failed to enter the text into the element: " + e.getMessage());
        }
    }




}


