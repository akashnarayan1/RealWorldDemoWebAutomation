package Hooks;

import Managers.FileReaderManager;
import PageObjects.ArticlePage;
import PageObjects.HomePage;
import Utilities.FileUtility;
import Utilities.TestContext;
import Utilities.Wait;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Hooks {

    static TestContext testContext;
    WebDriver webDriver;

    HomePage homePage;

    ArticlePage articlePage;

    FileUtility fileUtility;

    public Hooks(TestContext context) throws IOException {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        articlePage = testContext.getPageObjectManager().getArticlePage();
        fileUtility = new FileUtility();
    }

    @Before
    public void setUp() throws IOException {
        webDriver = testContext.getDriverManager().getDriver();
        webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
        fileUtility.setupUserDetails();
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        String screenshotName = scenario.getName().replaceAll(" ", "_");
        if(scenario.isFailed()) {
            try {
                File sourcePath = ((TakesScreenshot)testContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);

                File destinationPath = new File(System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + ".png");
                Files.copy(sourcePath, destinationPath);
//                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        testContext.getDriverManager().closeDriver();
    }
}
