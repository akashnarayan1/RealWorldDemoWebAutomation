package Managers;

import PageObjects.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Class to manage the page objects
 * initializes the pages and returns as objects
 */
public class PageObjectManager {
    private final WebDriver webDriver;
    private HomePage homePage;
    private SigninPage loginPage;

    private SignupPage signupPage;

    private SettingsPage settingsPage;

    private EditorPage editorPage;

    private ArticlePage articlePage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Short Hand If...Else
    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(webDriver) : homePage;
    }

    //General If...Else
    public SignupPage getSignupPage() {
        return (signupPage == null) ? signupPage = new SignupPage(webDriver) : signupPage;
    }

    public SigninPage getSigninPage() {
        return (loginPage == null) ? loginPage = new SigninPage(webDriver) : loginPage;
    }

    public SettingsPage getSettingPage() {
        return (settingsPage == null) ? settingsPage = new SettingsPage(webDriver) : settingsPage;
    }

    public EditorPage getEditorPage() throws IOException {
        return (editorPage == null) ? editorPage = new EditorPage(webDriver) : editorPage;
    }

    public ArticlePage getArticlePage() throws IOException {
        return (articlePage == null) ? articlePage = new ArticlePage(webDriver) : articlePage;

    }
}
