package StepDefinitions;

import PageObjects.SettingsPage;
import Utilities.TestContext;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SettingsSteps {

    SettingsPage settingsPage;

    TestContext testContext;

    WebDriver webDriver;

    public SettingsSteps(TestContext context) {
        testContext = context;
        settingsPage = testContext.getPageObjectManager().getSettingPage();
        webDriver = testContext.getDriverManager().getDriver();
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        try {
            settingsPage.getLogoutBtn().click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", settingsPage.getLogoutBtn());
            if(!settingsPage.getLogoutBtn().isDisplayed())
                throw (e);
            settingsPage.getLogoutBtn().click();
        }
    }
}
