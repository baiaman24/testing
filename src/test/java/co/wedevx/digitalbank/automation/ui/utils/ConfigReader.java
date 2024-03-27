package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//logic that reads config file(properties file)
public class ConfigReader {
    private static Properties properties;
   static {
        //filepath --> the directory of your properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //class enables to read files
        //it throws checked exception
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPropertiesValue(String key){
       return properties.getProperty(key);
    }
}
