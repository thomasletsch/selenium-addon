package org.vaadin.addons.javaee.selenium;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles test values for form fields inside properties files. Base class for all data driven functionality (setting of values, validation
 * etc.). To be overwritten for loading specific property files.
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public abstract class DataDriven {

    private static Logger log = LoggerFactory.getLogger(DataDriven.class);

    private Map<String, Properties> cache = new HashMap<>();

    /**
     * Returns the value for the key "entityName.attribute" from the loaded property files.
     */
    public String getDefaultValue(String entityName, String attribute) {
        Properties properties = loadProperties(entityName, null);
        String value = (String) properties.get(attribute);
        return value;
    }

    /**
     * Returns all keys from the loaded property files.
     */
    public List<String> getAllKeys(String entityName, String extension) {
        Properties properties = loadProperties(entityName, extension);
        List<String> result = new ArrayList<>();
        for (Object key : properties.keySet()) {
            result.add((String) key);
        }
        return result;
    }

    /**
     * Load the property file named "&lt;entityName&gt;-&lt;extension&gt;.properties"
     */
    protected Properties loadProperties(String entityName, String extension) {
        String propertyFile = entityName;
        if (!StringUtils.isBlank(extension)) {
            propertyFile = propertyFile + "_" + extension;
        }
        if (cache.containsKey(propertyFile)) {
            return cache.get(propertyFile);
        }
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(propertyFile + ".properties");
            properties.load(resourceAsStream);
        } catch (Exception e) {
            log.error("Could not load " + propertyFile + ".properties", e);
            throw new RuntimeException("Could not load " + propertyFile + ".properties", e);
        }
        cache.put(propertyFile, properties);
        return properties;
    }

}