package main.java.com.paulosantos.util;

public abstract class Config {

    public static String getProperty(String key) {
        ReadProperty readProperty = ReadProperty.getInstance();
        return readProperty.getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue) {
        ReadProperty readProperty = ReadProperty.getInstance();
        return readProperty.getProperty(key, defaultValue);
    }
}
