package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverSupplier {
    public static WebDriver firefoxDriver;
    public static WebDriver chromeDriver;
    public static WebDriver operaDriver;

    public static WebDriver getFirefoxDriver() {
        if (firefoxDriver == null)
            firefoxDriver = new FirefoxDriver();

        return firefoxDriver;
    }

    public static WebDriver getChromeDriver() {
        if (chromeDriver == null)
            chromeDriver = new FirefoxDriver();

        return chromeDriver;
    }

    public static WebDriver getOperaDriver() {
        if (operaDriver == null)
            operaDriver = new OperaDriver();

        return operaDriver;
    }

    public static WebDriver getDriver(String browserName){
        switch (browserName){
            case "Google Chrome": return getChromeDriver();
            case "Opera": return getOperaDriver();
            case "Mozila Firefox": return getFirefoxDriver();
            default: throw new IllegalArgumentException("unknown browser");
        }
    }
}
