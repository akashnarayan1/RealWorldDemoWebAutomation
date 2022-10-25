package StepDefinitions;

import PageObjects.HomePage;
import PageObjects.SignupPage;
import Utilities.TestContext;
import Utilities.Wait;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SignupSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignupSteps.class);
    TestContext testContext;
    HomePage homePage;
    SignupPage signupPage;
    WebDriver webDriver;

    public SignupSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        signupPage = testContext.getPageObjectManager().getSignupPage();
        webDriver = testContext.getDriverManager().getDriver();

    }

    @When("^I signup using a new user$")
    public void iSignupUsingANewUser() throws Exception {
        homePage.getSignUpBtn().click();
        signupPage.signUp();
        LOGGER.info("Signup complete. Waiting to arrive on the home screen.");
        Wait.untilElementIsVisible(webDriver, homePage.getNewArticleBtn(), Duration.ofSeconds(5));
    }
}
