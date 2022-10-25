package DataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserDetailsFileReader {
    private final Properties properties;

    public UserDetailsFileReader() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String propertyFilePath = "config/userDetails.properties";

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

    public String getUsername() {
        String username = properties.getProperty("username");

        //Simply If...Else
        if (username != null) return username;
        else
            throw new RuntimeException("username not specified in the userDetails file.");
    }

    public String getEmail() {
        String email = properties.getProperty("email");

        //Simply If...Else
        if (email != null) return email;
        else
            throw new RuntimeException("email not specified in the userDetails file.");
    }

    public String getPassword() {
        String password = properties.getProperty("password");

        //Simply If...Else
        if (password != null) return password;
        else
            throw new RuntimeException("password not specified in the userDetails file.");
    }

    public String getTmpUsername() {
        String username = properties.getProperty("usernameTmp");

        //Simply If...Else
        if (username != null) return username;
        else
            throw new RuntimeException("username not specified in the userDetails file.");
    }

    public String getTmpEmail() {
        String email = properties.getProperty("emailTmp");

        //Simply If...Else
        if (email != null) return email;
        else
            throw new RuntimeException("email not specified in the userDetails file.");
    }

    public String getTmpPassword() {
        String password = properties.getProperty("passwordTmp");

        //Simply If...Else
        if (password != null) return password;
        else
            throw new RuntimeException("password not specified in the userDetails file.");
    }
}
