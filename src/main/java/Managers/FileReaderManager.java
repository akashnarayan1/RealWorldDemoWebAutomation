package Managers;

import DataProviders.ConfigFileReader;
import DataProviders.UserDetailsFileReader;

/**
 * Class to manage reading different config files
 */
public class FileReaderManager {
    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileReader configFileReader;

    private static UserDetailsFileReader userDetailsFileReader;

    private FileReaderManager() {}

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigFileReader getConfigFileReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }

    public UserDetailsFileReader userDetailsFileReader(){
        return (userDetailsFileReader == null) ? new UserDetailsFileReader() : userDetailsFileReader;
    }
}
