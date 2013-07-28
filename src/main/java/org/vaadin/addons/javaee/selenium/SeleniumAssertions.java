package org.vaadin.addons.javaee.selenium;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vaadin.addons.javaee.selenium.input.InputMethod;
import org.vaadin.addons.javaee.selenium.input.InputMethodFactory;

/**
 * Several methods to assert values of fields inside a form.
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public class SeleniumAssertions {

    private WebDriver driver;

    private InputMethodFactory inputMethodFactory;

    public SeleniumAssertions(WebDriver driver) {
        this.driver = driver;
        inputMethodFactory = new InputMethodFactory(driver);
    }

    /**
     * Validates the value of the field with the id "&lt;entityName&gt;.&lt;attribute&gt;" against the given text.
     */
    public void assertText(String entityName, String attribute, String text) {
        InputMethod inputMethod = inputMethodFactory.get(entityName, attribute);
        inputMethod.assertInput(entityName, attribute, text);
    }

    /**
     * Validates the value of the field with the given id against the given text.
     */
    public void assertText(String id, String text) {
        InputMethod inputMethod = inputMethodFactory.get(id);
        inputMethod.assertInput(id, text);
    }

    /**
     * Checks that no (Vaadin) errors are shown on the current page.
     */
    public void assertNoError() {
        List<WebElement> elements = driver.findElements(By.className("v-Notification"));
        assertTrue("Error Element found!", elements.isEmpty());
    }

}
