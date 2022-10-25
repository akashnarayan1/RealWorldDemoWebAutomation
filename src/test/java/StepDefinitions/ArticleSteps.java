package StepDefinitions;

import PageObjects.ArticlePage;
import PageObjects.EditorPage;
import PageObjects.HomePage;
import Utilities.TestContext;
import Utilities.Wait;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ArticleSteps {

    TestContext testContext;

    HomePage homePage;

    ArticlePage articlePage;

    EditorPage editorPage;

    WebDriver webDriver;

    public ArticleSteps(TestContext context) throws IOException {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        articlePage = testContext.getPageObjectManager().getArticlePage();
        editorPage = testContext.getPageObjectManager().getEditorPage();
        webDriver = testContext.getDriverManager().getDriver();
    }

    @Then("^I verify that the article is published(?: (.*))?$")
    public void iVerifyThatTheArticleIsPublished(String edit) {
        if(edit != null) {
            articlePage.verifyArticlePublished("test");
        }
        else {
            articlePage.verifyArticlePublished();
        }
    }

    @And("I delete the published article")
    public void iDeleteThePublishedArticle() throws Exception {
        articlePage.deleteTheArticle();
        Wait.untilElementIsVisible(webDriver, homePage.getNewArticleBtn(), Duration.ofSeconds(5));
    }

    @When("I favourite the article")
    public void iFavouriteTheArticle() throws Exception {
        articlePage.likeAnArticle();
    }

    @Then("I verify that the article is favourited")
    public void iVerifyThatTheArticleIsFavourited() {
        Assert.assertTrue(articlePage.getFavouriteUnfavouriteBtn().getText().contains("Unfavorite"));
    }

    @When("I post a comment {string} on the article")
    public void iPostACommentOnTheArticle(String comment) {
        articlePage.postACommentOnTheArticle(comment);
    }

    @Then("I verify that the comment {string} is posted successfully")
    public void iVerifyThatTheCommentIsPostedSuccessfully(String comment) {
        Assert.assertEquals(articlePage.getFirstCommentFromList().getText(), comment);
    }

    @When("I follow the author of the article")
    public void iFollowTheAuthorOfTheArticle() throws InterruptedException {
        articlePage.followTheAuthorOfTheArticle();
    }

    @Then("I verify that the user under test is following the author")
    public void iVerifyThatTheUserUnderTestIsFollowingTheAuthor() {
        Assert.assertTrue(articlePage.getFollowUnfollowBtn().getText().contains("Unfollow"));
    }

    @And("I delete the comment")
    public void iDeleteTheComment() {
        articlePage.getDeleteCommentBtn().click();
    }
}
