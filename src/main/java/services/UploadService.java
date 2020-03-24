package services;

import entities.Good;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.PropertiesSupplier;
import util.WebDriverSupplier;

import java.util.Properties;

public class UploadService {
    private static UploadService uploadService;
    private final String AUTHORIZATION_URI;
    public WebDriver driver;
    private Properties appProperties;
    private boolean isLoggedIn;

    private UploadService() {
        this.driver = WebDriverSupplier.getFirefoxDriver();
        this.appProperties = PropertiesSupplier.getAppProperties();
        this.AUTHORIZATION_URI = appProperties.getProperty("site_authorization_url");
        this.isLoggedIn = false;
    }

    public static UploadService getInstance() {
        if (uploadService == null)
            uploadService = new UploadService();

        return uploadService;
    }

    public void uploadToBookDom(Good good, boolean publish) {
        if (!isLoggedIn) {
            logIn();
        }
        driver.get(appProperties.getProperty("new_product_url"));
        driver.findElement(By.name("post_title")).sendKeys(good.getTitleOfGood());
        driver.findElement(By.id("_regular_price")).sendKeys(String.valueOf(good.getPrice()));
        driver.findElement(By.name("aiosp_title")).sendKeys(good.getTitleOfGood());
        driver.findElement(By.name("aiosp_description")).sendKeys(getShortDescription(good.getDescription()));
        driver.findElement(By.name("aiosp_keywords")).sendKeys(good.getKeyWordsStr());
        driver.findElement(By.id("product_cat-all"))
                .findElement(By.id("product_catchecklist"))
                .findElements(By.tagName("label"))
                .stream()
                .filter(el -> good.getCategories().contains(el.getText()))
                .forEach(WebElement::click);
        driver.findElement(By.id("link-product_tag")).click();
        driver.switchTo()
                .frame(driver.findElement(By.id("excerpt_ifr")))
                .findElement(By.id("tinymce"))
                .sendKeys(good.getDescription());
        if (publish) {
            driver.switchTo().defaultContent().findElement(By.id("publish")).click();
        }
    }

    public String getShortDescription(String fullDescription) {
        int finalIndex = 160;
        for (int i = 50; i < 185; i++) {
            if (fullDescription.charAt(i) == '.') {
                finalIndex = i;
                break;
            }
        }
        return fullDescription.substring(0, finalIndex);
    }

    public void logIn() {
        driver.manage().window().maximize();
        driver.get(AUTHORIZATION_URI);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.name("login"));

        usernameField.sendKeys(appProperties.getProperty("admin_username"));
        passwordField.sendKeys(appProperties.getProperty("admin_password"));
        loginBtn.click();

        isLoggedIn = true;
    }
}