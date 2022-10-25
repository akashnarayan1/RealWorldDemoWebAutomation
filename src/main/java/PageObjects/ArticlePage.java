package PageObjects;

import Utilities.Wait;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ArticlePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePage.class);

    FileReader reader;

    Properties properties;

    WebDriver webDriver;

    public ArticlePage(WebDriver webDriver) throws IOException {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        reader = new FileReader("config/ArticleData.properties");
        properties = new Properties();
        properties.load(reader);
    }

    @FindBy(xpath = "//h1[contains(@ng-bind,'::$ctrl.article.title')]")
    private WebElement articleTitle;

    @FindBy(xpath = "//button[contains(@ng-click,'$ctrl.deleteArticle()')]")
    private WebElement deleteArticleBtn;

    @FindBy(xpath = "//a[contains(@ui-sref,'app.editor({ slug: $ctrl.article.slug })')]")
    private WebElement editArticleBtn;

    @FindBy(xpath = "//button[contains(@ng-class,'$ctrl.article.favorited')]")
    private WebElement favouriteUnfavouriteBtn;

    @FindBy(xpath = "//textarea[contains(@ng-model,'$ctrl.commentForm.body')]")
    private WebElement commentTextBox;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement postCommentBtn;

    @FindBy(xpath = "//i[contains(@ng-click,'$ctrl.deleteCb()')]")
    private WebElement deleteCommentBtn;

    @FindBys({
            @FindBy(xpath = "//p[contains(@ng-bind,'::$ctrl.data.body')]")
    })
    private List<WebElement> commentList;

    @FindBy(xpath = "//button[contains(@ng-class,'$ctrl.user.following')]")
    private WebElement followUnfollowBtn;

    public WebElement getFollowUnfollowBtn() {
        return followUnfollowBtn;
    }

    public List<WebElement> getCommentList() {
        return commentList;
    }

    public WebElement getFirstCommentFromList() {
        return commentList.get(0);
    }

    public WebElement getPostCommentBtn() {
        return postCommentBtn;
    }

    public WebElement getDeleteCommentBtn() {
        return deleteCommentBtn;
    }

    public WebElement getCommentTextBox() {
        return commentTextBox;
    }
    public WebElement getFavouriteUnfavouriteBtn() {
        return favouriteUnfavouriteBtn;
    }

    public WebElement getEditArticleBtn() {
        return editArticleBtn;
    }

    public WebElement getDeleteArticleBtn() {
        return deleteArticleBtn;
    }

    public WebElement getArticleTitle() {
        return articleTitle;
    }

    public void verifyArticlePublished(String... edited) {
        Wait.untilElementIsVisible(webDriver, getArticleTitle(), Duration.ofSeconds(5));
        String articleTitle;
        if(edited.length > 0) {
            articleTitle = properties.getProperty("articleTitle") + " " + edited[0];
        }
        else {
            articleTitle = properties.getProperty("articleTitle");
        }
        Assert.assertEquals(getArticleTitle().getText(), articleTitle);
        Assert.assertTrue(webDriver.getCurrentUrl().contains(articleTitle.replaceAll(" ", "-")));
    }

    public void deleteTheArticle() throws Exception {
        if(!getArticleTitle().isDisplayed()) {
            throw new Exception ("Not on the published article page");
        }
        getDeleteArticleBtn().click();
    }

    public void likeAnArticle() throws Exception {
        Wait.untilElementIsVisible(webDriver, getCommentTextBox(), Duration.ofSeconds(5));
        // check if already favourite
        int count = Integer.parseInt(getFavouriteUnfavouriteBtn().getText().replaceAll("[\\D]", ""));
        int tries = 10;
        boolean success = false;
        if(getFavouriteUnfavouriteBtn().getText().contains("Unfavorite")) {
            LOGGER.info("Already set to favourite, Making it Unfavourite");
            getFavouriteUnfavouriteBtn().click();

            while(tries > 0) {
                if(Integer.parseInt(getFavouriteUnfavouriteBtn().getText().replaceAll("[\\D]", "")) == count - 1) {
                    success = true;
                    count -= 1;
                    LOGGER.info("Unfavourite Successful");
                    break;
                }
                tries -= 1;
                Thread.sleep(1000);
            }
            if(!success)
                throw new Exception("Unable to make it unfavourite in 10 secs. Check if the favourite/unfavourite button is working");
        }
        getFavouriteUnfavouriteBtn().click();
        tries = 10;

        while(tries > 0) {
            if(Integer.parseInt(getFavouriteUnfavouriteBtn().getText().replaceAll("[\\D]", "")) == count + 1) {
                success = true;
                LOGGER.info("Favourite Successful");
                break;
            }
            tries -= 1;
            Thread.sleep(1000);
        }
        if(!success)
            throw new Exception("Unable to favourite it in 10 secs. Check if the favourite/unfavourite button is working");
    }

    public void postACommentOnTheArticle(String comment) {
        Wait.untilElementIsVisible(webDriver, getFirstCommentFromList(), Duration.ofSeconds(5));
        getCommentTextBox().sendKeys(comment);
        try {
            getPostCommentBtn().click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", getPostCommentBtn());
            if(!getPostCommentBtn().isDisplayed())
                throw (e);
            getPostCommentBtn().click();
        }
        Wait.untilElementIsVisible(webDriver, getDeleteCommentBtn(), Duration.ofSeconds(5));
    }

    public void followTheAuthorOfTheArticle() throws InterruptedException {
        Wait.untilElementIsVisible(webDriver, getFirstCommentFromList(), Duration.ofSeconds(5));
        // check if already following
        if(getFollowUnfollowBtn().getText().contains("Unfollow")) {
            LOGGER.info("This user is already being followed. Unfollowing now for test");
            getFollowUnfollowBtn().click();
            Thread.sleep(2000);
        }
        getFollowUnfollowBtn().click();
        Thread.sleep(2000);
    }
}
