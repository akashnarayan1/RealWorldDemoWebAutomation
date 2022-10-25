package Managers;

import Enums.DriverType;
import Enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Class to manage the selenium drivers
 * using WebDriverManager for driver management
 */
public class DriversManager {
    private WebDriver webDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public DriversManager() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    /**
     * Method to create a local driver based on the browser being used
     * @return WebDriver
     */
    private WebDriver createLocalDriver() {
        switch (driverType) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--window-size=1644,868");
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().window().maximize();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                webDriver = new FirefoxDriver(firefoxOptions);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
            }
            case SAFARI -> webDriver = new SafariDriver();
        }
        long time = FileReaderManager.getInstance().getConfigFileReader().getTime();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
        webDriver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(time));
        return webDriver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("Remote web driver is not yet implemented");
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL -> webDriver = createLocalDriver();
            case REMOTE -> webDriver = createRemoteDriver();
        }
        return webDriver;
    }

    /**
     * Method to get the driver in the current context
     * @return WebDriver
     */
    public WebDriver getDriver() {
        if (webDriver == null) webDriver = createDriver();
        return webDriver;
    }

    public void closeDriver() {
        webDriver.close();
        webDriver.quit();
    }
}
