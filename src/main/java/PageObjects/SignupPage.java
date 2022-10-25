package PageObjects;

import Managers.FileReaderManager;
import Utilities.Wait;
import com.cucumber.listener.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SignupPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignupPage.class);

    WebDriver webDriver;

    public SignupPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameTextBox;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    public WebElement getSignUpBtn() {
        return signUpBtn;
    }

    public WebElement getUsernameTextBox() {
        return usernameTextBox;
    }

    public WebElement getEmailTextBox() {
        return emailTextBox;
    }

    public WebElement getPasswordTextBox() {
        return passwordTextBox;
    }

    public void signUp() throws Exception {
        LOGGER.info("Initiating the Signup flow");
        Wait.untilElementIsVisible(webDriver, getSignUpBtn(), Duration.ofSeconds(5));
        if(!getSignUpBtn().isDisplayed()) {
            LOGGER.error(" Could not load the Sign up page");
            throw new Exception("Sign Up page has not loaded");
        }
        getUsernameTextBox().sendKeys(FileReaderManager.getInstance().userDetailsFileReader().getTmpUsername());
        getEmailTextBox().sendKeys(FileReaderManager.getInstance().userDetailsFileReader().getTmpEmail());
        getPasswordTextBox().sendKeys(FileReaderManager.getInstance().userDetailsFileReader().getTmpPassword());
        getSignUpBtn().click();
    }
}
