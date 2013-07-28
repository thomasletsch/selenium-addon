package org.vaadin.addons.javaee.selenium.input;

/**
 * Encapsulates an element on the page which has a value. It is the basic interface for all vaadin element specific read and write
 * utilities. Each element gets its implementation. To extend it, make a subclass (better from AbstractInputMethod) and add it to the
 * InputMethodFactory (or create your own).
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public interface InputMethod {

    /**
     * Sets the text value to the element with the given id.
     */
    void input(String id, String text);

    /**
     * Sets the text value to the element with the id "&lt;entityName&gt;.&lt;attribute&gt;".
     */
    void input(String entityName, String attribute, String text);

    /**
     * Retrieves the text value of the element with the id "&lt;entityName&gt;.&lt;attribute&gt;".
     */
    String value(String entityName, String attribute);

    /**
     * Retrieves the text value to the element with the given id.
     */
    String value(String id);

    /**
     * Asserts the value of the element with the id "&lt;entityName&gt;.&lt;attribute&gt;" against the given text.
     */
    void assertInput(String entityName, String attribute, String text);

    /**
     * Asserts the value of the element with the given id against the given text.
     */
    void assertInput(String id, String text);

    /**
     * Returns true if this InputMethod can handle the element with the id "&lt;entityName&gt;.&lt;attribute&gt;".
     */
    boolean accepts(String entityName, String attribute);

    /**
     * Returns true if this InputMethod can handle the element with the given id.
     */
    boolean accepts(String id);
}
