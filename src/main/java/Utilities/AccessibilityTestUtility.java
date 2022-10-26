package Utilities;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AccessibilityTestUtility {
    AxeBuilder builder;
    WebDriver webDriver;

    public AccessibilityTestUtility(WebDriver webdriver){
        builder = new AxeBuilder();
        this.webDriver = webdriver;

    }

    public void accessibilityTest(String pageName) throws FileNotFoundException {
        Results results = builder.analyze(webDriver);
        List<Rule> violations = results.getViolations();
        if (violations.size() == 0)
        {
            Assert.assertTrue(true, "No violations found");
        }
        else
        {
            JsonParser jsonParser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            AxeReporter.writeResultsToJsonFile("reports/accessibility/accessibilityTestReport", results);
            JsonElement jsonElement = jsonParser.parse(new FileReader("reports/accessibility/accessibilityTestReport" + ".json"));
            String prettyJson = gson.toJson(jsonElement);
            AxeReporter.writeResultsToTextFile("reports/accessibility/accessibilityTestReport", prettyJson);
            Assert.assertEquals(violations.size(), 0, violations.size() + " violations found");
        }
    }
}
