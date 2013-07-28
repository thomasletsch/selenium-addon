package org.vaadin.addons.javaee.selenium;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Handles automatic setting of field values inside a form.
 * 
 * It expects the form to have a certain name, the entityName and the fields to be constructed like &lt;entityName&gt;.&lt;attributeName&gt;. If you
 * follow this convention, this utility can fill your form automatically for you.
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public class DataDrivenActions extends DataDriven {

    private SeleniumActions actions;

    public DataDrivenActions(SeleniumActions actions) {
        this.actions = actions;
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;.properties" and tries to set all defined values in the current form.
     */
    public void setDefaultValues(String entityName) {
        setDefaultValues(entityName, null);
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;-&lt;extension&gt;.properties" and tries to set all defined values in the current
     * form.
     */
    public void setDefaultValues(String entityName, String extension) {
        Properties properties = loadProperties(entityName, extension);
        for (Object obj : properties.keySet()) {
            String attribute = (String) obj;
            String property = properties.getProperty(attribute);
            if (!StringUtils.isBlank(property)) {
                actions.input(entityName, attribute, property);
            }
        }
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;.properties" and tries to set the defined value for the field with the id
     * "&lt;entityName&gt;.&lt;attribute&gt;".
     */
    public void setDefaultValues(String entityName, String extension, String attribute) {
        Properties properties = loadProperties(entityName, extension);
        actions.input(entityName, attribute, properties.getProperty(attribute));
    }

}
