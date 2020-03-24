package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesSupplier {
    private static Properties appProperties;


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
