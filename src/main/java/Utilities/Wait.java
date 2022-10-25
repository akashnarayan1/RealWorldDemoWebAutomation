package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Class to handle custom explicit wait
 */
public class Wait {

    private static WebDriverWait webDriverWait;

    public static void until(WebDriver webDriver, Duration duration, Function<WebDriver, Boolean> waitCondition) {
        webDriverWait = new WebDriverWait(webDriver, duration);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            //To-Do
        }
    }

    /**
     * Method to wait until an element is visible
     * @param webDriver Driver in use
     * @param webElement Element to be located
     * @param duration Duration to wait
     */
    public static void untilElementIsVisible(WebDriver webDriver, WebElement webElement, Duration duration) {
        webDriverWait = new WebDriverWait(webDriver, duration);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Method to wait until an element is clickable
     * @param webDriver Driver in use
     * @param webElement Element to be located
     * @param duration Duration to wait
     */
    public static void untilElementIsClickable(WebDriver webDriver, WebElement webElement, Duration duration) {
        webDriverWait = new WebDriverWait(webDriver, duration);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
