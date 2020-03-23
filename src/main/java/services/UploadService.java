package services;

import entities.Good;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.SettingsUtil;
import util.WebDriverSupplier;

import java.util.Properties;

public class UploadService {
    private static UploadService uploadService;
    private final String AUTHORIZATION_URI;
    private Properties book_dom_properties;
    public WebDriver driver;
    private boolean isLoggedIn;

    private UploadService() {
        this.driver = WebDriverSupplier.getFirefoxDriver();
        this.book_dom_properties = SettingsUtil.getBookDomProperties();
        this.AUTHORIZATION_URI = book_dom_properties.getProperty("site_authorization_url");
        this.isLoggedIn=false;
    }

    public void uploadToBookDom(Good good, boolean publish) {
        if(!isLoggedIn){
            logIn();
        }
        driver.get(book_dom_properties.getProperty("new_product_url"));
        driver.findElement(By.name("post_title")).sendKeys(good.getTitleOfGood());
        driver.findElement(By.id("_regular_price")).sendKeys(String.valueOf(good.getPrice()));
        driver.findElement(By.name("aiosp_title")).sendKeys(good.getTitleOfGood());
        driver.findElement(By.name("aiosp_description")).sendKeys(good.getDescription().substring(0,160));
        driver.findElement(By.name("aiosp_keywords")).sendKeys(good.getKeyWordsStr());
        driver.findElement(By.id("product_cat-all"))
                .findElement(By.id("product_catchecklist"))
                .findElements(By.tagName("label"))
                .stream()
                .filter(el->good.getCategories().contains(el.getText()))
                .forEach(WebElement::click);
        driver.switchTo()
                .frame(driver.findElement(By.id("excerpt_ifr")))
                .findElement(By.id("tinymce"))
                .sendKeys(good.getDescription());
        if(publish){
            driver.switchTo().defaultContent().findElement(By.id("publish")).click();
        }
    }

    public void logIn() {
        driver.manage().window().maximize();
        driver.get(AUTHORIZATION_URI);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.name("login"));

        usernameField.sendKeys(book_dom_properties.getProperty("admin_username"));
        passwordField.sendKeys(book_dom_properties.getProperty("admin_password"));
        loginBtn.click();

        isLoggedIn=true;
    }

    public static UploadService getInstance() {
        if(uploadService==null)
            uploadService=new UploadService();

        return uploadService;
    }
}