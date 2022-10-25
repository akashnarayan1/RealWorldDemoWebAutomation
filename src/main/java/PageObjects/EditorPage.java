package PageObjects;

import Utilities.Wait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class EditorPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditorPage.class);

    FileReader reader;

    Properties properties;

    WebDriver webDriver;

    public EditorPage(WebDriver webDriver) throws IOException {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        try {
            reader = new FileReader("config/ArticleData.properties");
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            LOGGER.error("ArticleData.properties does not exists on the path specified. Error: " + e);
            throw new IOException("File not found: " + e);
        }
    }

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.article.title')]")
    private WebElement articleTitleTextBox;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.article.description')]")
    private WebElement articleDescriptionTextBox;

    @FindBy(xpath = "//textarea[contains(@ng-model,'$ctrl.article.body')]")
    private WebElement articleBodyTextBox;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.tagField')]")
    private WebElement articleTagTextBox;

    @FindBy(xpath = "//button[contains(@ng-click,'$ctrl.submit()')]")
    private WebElement publishArticleBtn;

    @FindBys({
        @FindBy(xpath = "//*[text()[contains(., 'title must be unique')]]")
    })
    private List<WebElement> publishErrorMessage;

    public List<WebElement> getPublishErrorMessage() {
        return publishErrorMessage;
    }


    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebElement getArticleTitleTextBox() {
        return articleTitleTextBox;
    }

    public WebElement getPublishArticleBtn() {
        return publishArticleBtn;
    }

    public WebElement getArticleDescriptionTextBox() {
        return articleDescriptionTextBox;
    }

    public WebElement getArticleBodyTextBox() {
        return articleBodyTextBox;
    }

    public WebElement getArticleTagTextBox() {
        return articleTagTextBox;
    }

    public boolean publishArticle(boolean edit)  {
        getArticleTitleTextBox().clear();
        if(edit) {
            getArticleTitleTextBox().sendKeys(properties.getProperty("articleTitle") + " test");
        }
        else {
            getArticleTitleTextBox().sendKeys(properties.getProperty("articleTitle"));
        }
        getArticleDescriptionTextBox().clear();
        getArticleDescriptionTextBox().sendKeys(properties.getProperty("articleDescription"));
        getArticleBodyTextBox().clear();
        getArticleBodyTextBox().sendKeys(properties.getProperty("articleBody"));
        getArticleTagTextBox().clear();
        getArticleTagTextBox().sendKeys(properties.getProperty("articleTags"));
        getPublishArticleBtn().click();
        if(getPublishErrorMessage().size() > 0) {
            LOGGER.error("Article with same name already exists");
            return false;
        }
        return true;
    }

    public boolean editArticle() {
        return publishArticle(true);
    }
}
