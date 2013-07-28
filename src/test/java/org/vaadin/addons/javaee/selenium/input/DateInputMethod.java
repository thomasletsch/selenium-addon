package org.vaadin.addons.javaee.selenium.input;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateInputMethod extends AbstractInputMethod {

    public DateInputMethod(WebDriver driver) {
        super(driver);
    }

    @Override
    public void input(String id, String text) {
        WebElement dateInputField = getInputElement(id);
        // dateInputField.clear();
        dateInputField.sendKeys(text);
    }

    @Override
    public String value(String id) {
        WebElement element = getInputElement(id);
        return element.getAttribute("value");
    }

    @Override
    public void assertInput(String id, String text) {
        WebElement element = getInputElement(id);
        assertEquals(id, text, element.getAttribute("value"));
    }

    private WebElement getInputElement(String id) {
        WebElement dateInputField = driver.findElement(By.xpath("//div[@id='" + id + "']/input"));
        return dateInputField;
    }

    @Override
    protected String getElementClassAttribute() {
        return "v-datefield";
    }

}
