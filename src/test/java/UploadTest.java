import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import services.UploadService;
import util.SettingsUtil;

import java.util.ArrayList;
import java.util.List;

public class UploadTest {

    @Test
    public void catTest() {
        SettingsUtil.setUpApplication("Mozila Firefox");
        UploadService uploadService = UploadService.getInstance();
        uploadService.logIn();
        uploadService.driver.get("http://book-dom.com.ua/wp-admin/post-new.php?post_type=product");
        List<WebElement> cElements = uploadService.driver
                .findElement(By.id("product_cat-all"))
                .findElement(By.id("product_catchecklist"))
                .findElements(By.tagName("label"));

        List<String> categ = new ArrayList<String>();
        categ.add("Психология");
        categ.add("Психология воспитания");
        categ.add("Психология общения");
        categ.add("Менеджмент");
        System.out.println("-----------");
        cElements.stream()
                .filter(el -> categ.contains(el.getText().trim()))
                .peek(webElement -> System.out.println(webElement.getText()))
                .forEach(WebElement::click);
    }
}
