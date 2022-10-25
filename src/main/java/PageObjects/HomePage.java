package PageObjects;

import Utilities.TestContext;
import Utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//a[@href='#/login']")
    private WebElement signInBtn;

    @FindBy(xpath = "//a[@href='#/register']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//a[@href='#/']")
    private WebElement homeBtn;

    @FindBy(xpath = "//a[@href='#/editor/']")
    private WebElement newArticleBtn;

    @FindBy(xpath = "//a[@href='#/settings']")
    private WebElement settingsBtn;

    @FindBy(xpath = "//*[text()[contains(., 'Global Feed')]]")
    private WebElement globalFeed;

//    @FindBy(xpath = "//*[text()[contains(., 'welcome')]]")
//    private WebElement popularTag;

    @FindBys({
            @FindBy(xpath = "//h1[contains(@ng-bind,'$ctrl.article.title')]")
    })
    private List<WebElement> articleList;

    public List<WebElement> getArticleList() {
        return articleList;
    }

    public WebElement getPopularTag(String tagName) {
        return webDriver.findElement(By.xpath(String.format("//*[text()[contains(., '%s')]]", tagName)));
    }

    public WebElement getGlobalFeed() {
        return globalFeed;
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public WebElement getSignUpBtn() {
        return signUpBtn;
    }

    public WebElement getHomeBtn() {
        return homeBtn;
    }

    public WebElement getNewArticleBtn() {
        return newArticleBtn;
    }

    public WebElement getSettingsBtn() {
        return settingsBtn;
    }


}
