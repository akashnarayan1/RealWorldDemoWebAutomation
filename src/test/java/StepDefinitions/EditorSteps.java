package StepDefinitions;

import PageObjects.ArticlePage;
import PageObjects.EditorPage;
import PageObjects.HomePage;
import PageObjects.SignupPage;
import Utilities.TestContext;
import Utilities.Wait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class EditorSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditorSteps.class);

    TestContext testContext;

    HomePage homePage;

    HomeSteps homeSteps;

    EditorPage editorPage;

    ArticlePage articlePage;

    WebDriver webDriver;

    public EditorSteps(TestContext context) throws IOException {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        editorPage = testContext.getPageObjectManager().getEditorPage();
        articlePage = testContext.getPageObjectManager().getArticlePage();
        homeSteps = new HomeSteps(context);
        webDriver = testContext.getDriverManager().getDriver();
    }

    @When("I publish a new article")
    public void iPublishANewArticle() throws Exception {
        LOGGER.info("Initiating the publish flow");
        homePage.getNewArticleBtn().click();
        boolean isPublishSuccessful = editorPage.publishArticle(false);
        if(!isPublishSuccessful) {
            homeSteps.deleteCreatedArticles();
            homePage.getNewArticleBtn().click();
            editorPage.publishArticle(false);
        }
    }

    @When("I edit the published article")
    public void iEditThePublishedArticle() throws Exception {
        articlePage.getEditArticleBtn().click();
        Wait.untilElementIsVisible(webDriver, editorPage.getPublishArticleBtn(), Duration.ofSeconds(5));
        if(!editorPage.editArticle()) {
            homeSteps.deleteCreatedArticles();
            editorPage.editArticle();
        }

    }
}
