package org.vaadin.addons.javaee.selenium;

import java.util.Properties;

/**
 * Handles automatic assertion of field values inside a form.
 * 
 * It expects the form to have a certain name, the entityName and the fields to be constructed like &lt;entityName&gt;.&lt;attributeName&gt;. If you
 * follow this convention, this utility can check your form field values automatically against the defined values inside the properties
 * files..
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public class DataDrivenAssertions extends DataDriven {

    private SeleniumAssertions assertions;

    public DataDrivenAssertions(SeleniumAssertions assertions) {
        this.assertions = assertions;
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;.properties" and tries to assert all defined values in the current form.
     */
    public void assertDefaultValues(String entityName) {
        assertDefaultValues(entityName, null);
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;-&lt;extension&gt;.properties" and tries to assert all defined values in the
     * current form.
     */
    public void assertDefaultValues(String entityName, String extension) {
        Properties properties = loadProperties(entityName, extension);
        for (Object obj : properties.keySet()) {
            String attribute = (String) obj;
            assertions.assertText(entityName, attribute, properties.getProperty(attribute));
        }
    }

    /**
     * Loads the properties from a file named "&lt;entityName&gt;.properties" and tries to assert the defined value for the field with the id
     * "&lt;entityName&gt;.&lt;attribute&gt;".
     */
    public void assertDefaultValue(String entityName, String extension, String attribute) {
        Properties properties = loadProperties(entityName, extension);
        assertions.assertText(entityName, attribute, properties.getProperty(attribute));
    }

}
