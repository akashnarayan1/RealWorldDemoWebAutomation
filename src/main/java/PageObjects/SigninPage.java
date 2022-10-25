package PageObjects;

import Managers.FileReaderManager;
import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SigninPage {

    WebDriver webDriver;
    public SigninPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signinBtn;

    public WebElement getEmailTextBox() {
        return emailTextBox;
    }

    public WebElement getPasswordTextBox() {
        return passwordTextBox;
    }

    public WebElement getSigninBtn() {
        return signinBtn;
    }

    public void signin() {
        Wait.untilElementIsVisible(webDriver, getSigninBtn(), Duration.ofSeconds(5));
        getEmailTextBox().sendKeys(FileReaderManager.getInstance().userDetailsFileReader().getEmail());
        getPasswordTextBox().sendKeys(FileReaderManager.getInstance().userDetailsFileReader().getPassword());
        getSigninBtn().click();
    }
}
