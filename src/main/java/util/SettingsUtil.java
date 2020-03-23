package util;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SettingsUtil {
    public static Properties appProperties = new Properties();
    public static WebDriver webDriver;

    public static void setUpApplication(String browserName){
        if (appProperties.isEmpty()) {
            loadAppProperties();
        }
        switch (browserName){
            case "Google Chrome":{
                System.setProperty("webdriver.chrome.driver", appProperties.getProperty("path_to_geckodriver"));
                webDriver = WebDriverSupplier.getChromeDriver();
                break;
            }
            case "Opera": {
                System.setProperty("webdriver.opera.driver", appProperties.getProperty("path_to_geckodriver"));
                webDriver = WebDriverSupplier.getOperaDriver();
                break;
            }
            case "Mozila Firefox": {
                System.setProperty("webdriver.gecko.driver", appProperties.getProperty("path_to_geckodriver"));
                webDriver = WebDriverSupplier.getFirefoxDriver();
                break;
            }
            default: throw new IllegalArgumentException("unknown browser");
        }
    }

    public static Properties getBookDomProperties(){
        Properties bdProperties = new Properties();
        try {
            InputStream inputStream = SettingsUtil.class.getClassLoader().getResourceAsStream("book_dom.properties");
            bdProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bdProperties;
    }

    private static void loadAppProperties(){
        try {
            InputStream inputStream = SettingsUtil.class.getClassLoader().getResourceAsStream("application.properties");
            appProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



