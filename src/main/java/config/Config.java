package config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class Config {
    public static final String propertiesPath = "src/main/resources/application.properties";

    public static Properties setProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesPath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
