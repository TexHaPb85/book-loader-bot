package services;

import entities.Good;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.SettingsUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SovaBookDownloadService implements DownLoadService {
    private static SovaBookDownloadService instance;
    private final String siteHostURL = "sovabooks";
    private WebDriver webDriver;

    private SovaBookDownloadService() {
        webDriver = SettingsUtil.webDriver;
    }

    @Override
    public Good loadGoodByUrl(String url) {
        if (!url.contains(siteHostURL))
            throw new IllegalArgumentException("wrong url");

        webDriver.get(url);

        String author = webDriver
                .findElement(By.tagName("h2"))
                .getText()
                .trim();

        String goodTitle = webDriver
                .findElement(By.tagName("h1"))
                .findElement(By.tagName("span"))
                .getText();

        int price = Integer.parseInt(
                webDriver
                        .findElement(By.className("price"))
                        .getText()
                        .trim()
        );

        String description = webDriver
                .findElement(By.className("std"))
                .findElement(By.tagName("span"))
                .getText()
                .trim();

        List<WebElement> elements = webDriver
                .findElement(By.className("breadcrumbs"))
                .findElements(By.className("category"));
        elements.remove(elements.size() - 1);

        List<String> categories = elements.stream()
                .map(WebElement::getText)
                .flatMap(category -> Arrays.stream(category.split("[,.]")))
                .map(String::trim)
                .map(word -> word.replaceAll("[:,.-]", ""))
                .collect(Collectors.toList());

        return new Good(author, goodTitle, clearDescription(description), price, categories);
    }

    private String clearDescription(String description) {
        return Arrays.stream(description.split("[.]"))
                .filter(sentence -> !sentence.contains("sovabook") && !sentence.contains("com") && !sentence.contains("ua"))
                .reduce((s1, s2) -> s1 + "." + s2)
                .get();
    }

    public static SovaBookDownloadService getInstance() {
        if (instance == null)
            instance = new SovaBookDownloadService();

        return instance;
    }
}
