package org.vaadin.addons.javaee.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class to handle the well known selenium error StaleElementReferenceException. It provides a set of methods to safely ignore this
 * error and retry the retrieval until the time has ran out or the element / value could be retrieved.
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public class SaveElementAccess {

    private WebDriver driver;

    public SaveElementAccess(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the element by the given By method in a {@link StaleElementReferenceException} save kind.
     */
    public WebElement getElementSave(final By by) {
        WebElement element = null;
        FluentWait<WebDriver> wait = new WebDriverWait(driver, WaitConditions.LONG_WAIT_SEC, WaitConditions.SHORT_SLEEP_MS)
                .ignoring(StaleElementReferenceException.class);
        element = wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(by);
                if (element.isDisplayed()) {
                    return element;
                }
                return element;
            }
        });
        return element;
    }

    /**
     * A {@link StaleElementReferenceException} save version of getAttribute
     */
    public String getAttributeSave(final By by, final String attribute) {
        String text = null;
        FluentWait<WebDriver> wait = new WebDriverWait(driver, WaitConditions.LONG_WAIT_SEC, WaitConditions.SHORT_SLEEP_MS)
                .ignoring(StaleElementReferenceException.class);
        text = wait.until(new ExpectedCondition<String>() {

            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getAttribute(attribute);
            }
        });
        return text;
    }

    /**
     * A {@link StaleElementReferenceException} save version of getText
     */
    public String getTextSave(final By by) {
        String text = null;
        FluentWait<WebDriver> wait = new WebDriverWait(driver, WaitConditions.LONG_WAIT_SEC, WaitConditions.SHORT_SLEEP_MS)
                .ignoring(StaleElementReferenceException.class);
        text = wait.until(new ExpectedCondition<String>() {

            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
        return text;
    }

}
