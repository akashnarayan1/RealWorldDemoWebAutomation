package StepDefinitions;

import PageObjects.ArticlePage;
import PageObjects.HomePage;
import Utilities.AccessibilityTestUtility;
import Utilities.TestContext;

import Utilities.Wait;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HomeSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeSteps.class);

    TestContext testContext;

    AccessibilityTestUtility accessibilityTestUtility;

    HomePage homePage;

    ArticlePage articlePage;

    WebDriver webDriver;

    public HomeSteps(TestContext context) throws IOException {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        articlePage = testContext.getPageObjectManager().getArticlePage();
        webDriver = testContext.getDriverManager().getDriver();
        accessibilityTestUtility = new AccessibilityTestUtility(webDriver);
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {
        if(homePage.getSignInBtn().isDisplayed()) {
            LOGGER.info("Already on the home page");
        }
        else {
            LOGGER.info("Navigating to the home page");
            homePage.getHomeBtn().click();
        }
    }

    @Then("^I verify I am logged in successfully(?: after signup)?$")
    public void iVerifyIAmLoggedInSuccessfullyAfterSignup() {
        // To-Do Use username displayed on the screen for this validation
        Assert.assertTrue(homePage.getNewArticleBtn().isDisplayed());
    }

    @Then("^I verify I am logged out successfully$")
    public void iVerifyIAmLoggedOutSuccessfully() {
        Wait.untilElementIsVisible(webDriver, homePage.getSignInBtn(), Duration.ofSeconds(5));
        Assert.assertTrue(homePage.getSignInBtn().isDisplayed());
    }

    @And("I select {string} from popular tag list")
    public void iSelectTagFromPopularTagList(String tagName) {
        homePage.getPopularTag(tagName).click();
    }

    @And("I select the first article from the list")
    public void iSelectTheFirstArticleFromTheList() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> articleList = homePage.getArticleList();
        articleList.get(0).click();
    }

    public void deleteCreatedArticles() throws Exception {
        LOGGER.info("Deleting created articles");
        homePage.getHomeBtn().click();
        Thread.sleep(1000);
        boolean found = true;

        while (found) {
            homePage.getGlobalFeed().click();
            Wait.untilElementIsVisible(webDriver, homePage.getArticleList().get(0), Duration.ofSeconds(5));
            List<WebElement> articleList = homePage.getArticleList();
            // Assuming that the new articles will always be on top
            // To-Do Remove the hard coded name
            if(articleList.get(0).getText().contains("Reinforcement Learning")) {
                articleList.get(0).click();
                Thread.sleep(2000);
                articlePage.deleteTheArticle();
            }
            else {
                found = false;
            }
        }
    }

    @And("I run accessibility testing on {string} page")
    public void iRunAccessibilityTestingOnPage(String page) throws FileNotFoundException {
        //To-Do handle page input
        accessibilityTestUtility.accessibilityTest(page);
    }
}
