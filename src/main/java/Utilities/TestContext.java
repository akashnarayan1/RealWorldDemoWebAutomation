package Utilities;

import Managers.DriversManager;
import Managers.PageObjectManager;

/**
 * Class to manage current contect
 * Single class for getting DriverManager and PageObjectManager classes
 */
public class TestContext {
    private final DriversManager driverManager;
    private final PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;

    public TestContext() {
        driverManager = new DriversManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public DriversManager getDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
