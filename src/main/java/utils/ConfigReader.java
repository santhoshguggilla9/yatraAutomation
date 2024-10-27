package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // Properties object to store configuration key-value pairs
    private Properties properties = new Properties();

    /**
     * Constructor for ConfigReader.
     * Loads configuration properties from the file at "src/main/resources/config.properties".
     * Throws a RuntimeException if the file cannot be found or loaded.
     */
    public ConfigReader() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis); // Load properties from the config file
        } catch (IOException e) {
            // Log error and throw RuntimeException if file loading fails
            LogUtil.error("Failed to load config: " + e.getMessage());
            throw new RuntimeException("Configuration file not found.");
        }
    }

    /**
     * Retrieves the value associated with the specified key from the loaded properties.
     *
     * @param key The property key to look up in the configuration.
     * @return The value of the specified property, or null if the key is not found.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
