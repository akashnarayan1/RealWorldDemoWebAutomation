package DataProviders;

import Enums.DriverType;
import Enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private final Properties properties;

    public ConfigFileReader() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String propertyFilePath = "config/Configuration.properties";

        try {
            fileReader = new FileReader(propertyFilePath);
            bufferedReader = new BufferedReader(fileReader);
            properties = new Properties();

            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    /**
     * Method to fetch the URL under test
     * @return String URL
     */
    public String getUrl() {
        String url = properties.getProperty("url");

        //Simply If...Else
        if (url != null) return url;
        else
            throw new RuntimeException("url not specified in the config file.");
    }

    /**
     * Method to fetch timeout
     * @return long set timeout
     */
    public long getTime() {
        String timeout = properties.getProperty("timeout");

        //Common If...Else
        if (timeout != null) {
            return Long.parseLong(timeout);
        } else {
            throw new RuntimeException("timeout not specified in the config file.");
        }
    }

    /**
     * Method to get the browser to be used for testing
     * @return DriverType Enum to be used based on the browser
     */
    public DriverType getBrowser()  {
        String browserName = properties.getProperty("browser");

        return switch (browserName) {
            case "chrome" -> DriverType.CHROME;
            case "firefox" -> DriverType.FIREFOX;
            case "edge" -> DriverType.EDGE;
            case "safari" -> DriverType.SAFARI;
            default ->
                    throw new RuntimeException("Browser name key value in configuration file is not matched: " + browserName);
        };
    }

    /**
     * Method to fetch the environment to be used to testing.
     * Supported environments are LOCAL and REMOTE
     * @return EnvironmentType Enum
     */
    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");

        switch (environmentName) {
            case "local":
                return EnvironmentType.LOCAL;
            case "remote":
                return EnvironmentType.REMOTE;
            default:
                throw new RuntimeException("Environment type key value in configuration file is not matched: " + environmentName);
        }
    }

    public String getReportConfigPath(){
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
