package main.java.com.paulosantos.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {

    private static ReadProperty instance = null;
    private static Properties properties = null;

    private ReadProperty() {
        properties = new Properties();
        loadPropertiesFile();
    }

    public static ReadProperty getInstance() {
        if (instance == null) {
            instance = new ReadProperty();
        }
        return instance;
    }

    protected String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private void loadPropertiesFile() {
        try (InputStream file = getPropertyFile()) {
            if (properties != null) {
                properties.load(file);
            }
        } catch (IOException e) {
            System.out.println("Property file cannot be read");
        }
    }

    private InputStream getPropertyFile() {
        return ReadProperty.class.getClassLoader().getResourceAsStream("application.properties");
    }
}
