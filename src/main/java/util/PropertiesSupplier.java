package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesSupplier {
    private static Properties appProperties;
    private static Properties bookDomProperties;

    public static Properties getBookDomProperties() {
        if (bookDomProperties == null || bookDomProperties.isEmpty()) {
            bookDomProperties = new Properties();
            try {
                InputStream inputStream = SettingsUtil.class.getClassLoader().getResourceAsStream("book_dom.properties");
                bookDomProperties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bookDomProperties;
    }

    public static Properties getAppProperties() {
        if (appProperties == null || appProperties.isEmpty()) {
            appProperties = new Properties();
            try {
                InputStream inputStream = SettingsUtil.class.getClassLoader().getResourceAsStream("application.properties");
                appProperties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appProperties;
    }
}
