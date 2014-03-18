package org.vaadin.addons.javaee.selenium;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

/**
 * Basic Selenium test class. It uses the TakeScreenshot rule and loads the selenium config from the "env.properties" file.
 * 
 * @author Thomas Letsch (contact@thomas-letsch.de)
 * 
 */
public abstract class SeleniumTest {

    private static final String URL_KEY = "url";

    @Rule
    public static TakeScreenshot seleniumDriverRule = new TakeScreenshot();

    private String baseUrl;

    public SeleniumTest() {
        super();
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    @Before
    public void setUpSelenium() throws Exception {
        if (baseUrl == null) {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/env.properties"));
            baseUrl = properties.getProperty(URL_KEY);
        }
        seleniumDriverRule.getDriver().navigate().to(baseUrl);
    }

    @After
    public void tearDownSelenium() {

    }

}