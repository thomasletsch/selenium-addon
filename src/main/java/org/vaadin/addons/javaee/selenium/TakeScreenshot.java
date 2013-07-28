package org.vaadin.addons.javaee.selenium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TakeScreenshot extends TestWatcher {

    private static Logger log = LoggerFactory.getLogger(TakeScreenshot.class);

    public static final String SCREENSHOT_TARGET_DIRECTORY = "target/surefire-reports";

    public static final String PROPERTIES_FILE_NAME = "/env.properties";

    private static final String SAUCE_API_KEY_KEY = "SAUCE_ONDEMAND_ACCESS_KEY";

    private static final String SAUCE_USER_NAME_KEY = "SAUCE_ONDEMAND_USERNAME";

    private static final String SELENIUM_GRID_KEY = "seleniumGrid";

    private WebDriver driver;

    private boolean remote = false;

    private String seleniumGrid;

    public TakeScreenshot() {
    }

    @Override
    public void starting(Description desc) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            log.error("Could not load " + PROPERTIES_FILE_NAME, e);
        }
        if (!StringUtils.isBlank(properties.getProperty(SELENIUM_GRID_KEY))) {
            remote = true;
            seleniumGrid = properties.getProperty(SELENIUM_GRID_KEY);
        }
        setUpDriver();
    }

    @Override
    public void finished(Description desc) {
        log.info("Quitting driver...");
        driver.quit();
    }

    @Override
    public void failed(Throwable e, Description d) {
        log.info("Creating screenshot...");
        createScreenshot(d);
    }

    public void createScreenshot(Description d) {
        try {
            if (!(driver instanceof TakesScreenshot)) {
                log.debug("Augmenting driver " + driver);
                driver = new Augmenter().augment(driver);
                log.debug("Augmented driver " + driver);
            }
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            log.debug("TagesScreenshot " + takesScreenshot);
            File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String scrFilename = d.getTestClass().getSimpleName() + "#" + d.getMethodName() + "-failed.png";
            File directory = new File(SCREENSHOT_TARGET_DIRECTORY);
            directory.mkdirs();
            File outputFile = new File(directory, scrFilename);
            log.info(scrFilename + " screenshot created.");
            FileUtils.copyFile(scrFile, outputFile);
        } catch (Exception ioe) {
            log.error("Error creating screenshot.", ioe);
        }
    }

    /**
     * To be overwritten. This implementation works with Firefox local and on SauceLabs
     */
    protected void setUpDriver() {
        if (remote) {
            setUpRemoteDriver();
        } else {
            setUpLocalDriver();
        }
        driver.manage().timeouts().implicitlyWait(WaitConditions.DEFAULT_WAIT_SEC, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1200, 1024));
    }

    /**
     * To be overwritten. This implementation creates the Firefox local caps
     */
    protected void setUpLocalDriver() {
        driver = new FirefoxDriver();
    }

    /**
     * To be overwritten. This implementation creates a firefox SauceLabs cap
     */
    protected void setUpSauceLabsDriver() {
        String username = System.getenv(SAUCE_USER_NAME_KEY);
        String apiKey = System.getenv(SAUCE_API_KEY_KEY);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("platform", "Windows 7");
        capabilities.setCapability("version", "20");
        try {
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + apiKey + "@ondemand.saucelabs.com:80/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            log.error("Could not create remote webdriver on saucelabs", e);
        }
    }

    private void setUpRemoteDriver() {
        if (isSauceConfigSet()) {
            setUpSauceLabsDriver();
        } else {
            DesiredCapabilities capabillities = DesiredCapabilities.firefox();
            try {
                driver = new RemoteWebDriver(new URL(seleniumGrid), capabillities);
            } catch (MalformedURLException e) {
                log.error("Could not create a webdriver for seleniumGrid " + seleniumGrid, e);
            }
        }
    }

    public boolean isSauceConfigSet() {
        return System.getenv(SAUCE_USER_NAME_KEY) != null;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
