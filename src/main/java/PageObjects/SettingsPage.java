package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {

    public SettingsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//button[contains(@ng-click,'$ctrl.logout()')]")
    private WebElement logoutBtn;

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }
}
