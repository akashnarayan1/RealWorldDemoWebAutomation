package StepDefinitions;

import PageObjects.HomePage;
import PageObjects.SigninPage;
import Utilities.TestContext;
import Utilities.Wait;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SigninSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(SigninSteps.class);

    TestContext testContext;

    SigninPage signinPage;

    HomePage homePage;

    WebDriver webDriver;

    public SigninSteps(TestContext context) {
        testContext = context;
        signinPage = testContext.getPageObjectManager().getSigninPage();
        homePage = testContext.getPageObjectManager().getHomePage();
        webDriver = testContext.getDriverManager().getDriver();

    }

    @And("^I signin using a registered user$")
    public void iSigninUsingARegisteredUser() {
        LOGGER.info("Initiating Signin flow");
        if(homePage.getNewArticleBtn().isDisplayed()) {
            LOGGER.info("Already Logged in");
            return;
        }
        homePage.getSignInBtn().click();
        signinPage.signin();
        Wait.untilElementIsVisible(webDriver, homePage.getNewArticleBtn(), Duration.ofSeconds(5));
    }

    @And("^I navigate to Settings page$")
    public void iNavigateToSettingsPage() {
        LOGGER.info("Navigating to the Settings page");
        homePage.getSettingsBtn().click();
    }
}
