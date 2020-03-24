package util;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class SettingsUtil {
    public static WebDriver webDriver;

    public static void setUpApplication(String browserName) {
        Properties appProperties = PropertiesSupplier.getAppProperties();

        switch (browserName) {
            case "Google Chrome": {
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
            default:
                throw new IllegalArgumentException("unknown browser");
        }
    }

}



