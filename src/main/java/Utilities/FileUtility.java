package Utilities;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Class to for File Utilities
 */
public class FileUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtility.class);

    /**
     * Method to set user details and write them into the userDetails.properties file
     * @link <a href="https://fakerjs.dev/">...</a>
     * Using Faker to generate random email address and using them for sign up flow
     * Also sets a static set of credentials in the same file to be used for signin flows
     * @throws IOException
     */
    public void setupUserDetails() throws IOException {
        deleteFile("userDetails.properties", "config");
        createFile("userDetails.properties", "config");
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String username = email.split("@")[0];
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File("config/userDetails.properties"));

        properties.load(fileInputStream);

        properties.setProperty("usernameTmp", username);
        properties.setProperty("emailTmp", email);
        properties.setProperty("passwordTmp", username+"123");

        properties.setProperty("username", "akashtest101");
        properties.setProperty("email", "akashtest101@gmail.com");
        properties.setProperty("password", "akashtest101");

        properties.store(new FileOutputStream("config/userDetails.properties"), null);
    }

    /**
     * Method to delete a file specified
     * @param fileName file to be deleted
     * @param path path of the file to be deleted
     * @throws IOException
     */
    public void deleteFile(String fileName, String path) throws IOException {
        File file = new File(path + "/" + fileName);
        if(file.delete()) {
            //To-Do Add in logs
        }
    }

    /**
     * Method to create a new file
     * @param fileName file to be created
     * @param path path where the file need to be created
     * @throws IOException
     */
    public void createFile(String fileName, String path) throws IOException {
        File file = new File(path + "/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("Unable to create the " + fileName + " file: " + e);
            throw (e);
        }
    }

}
