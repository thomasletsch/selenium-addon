package org.vaadin.addons.javaee.selenium.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vaadin.addons.javaee.selenium.po.BasePO;
import org.vaadin.addons.javaee.selenium.WaitConditions;

public class CustomerNewPO extends BasePO {

    private static final String ADDRESS_CITY = "address.city";

    private static final String ADDRESS_ZIP = "address.zip";

    private static final String ENTITY = "Customer";

    public CustomerNewPO(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getIdentifyingElement() {
        return "CustomerNewPanel";
    }

    public CustomerOverviewPO createCustomer() {
        dataActions.setDefaultValues(ENTITY, null);
        actions.clickButton("button_save");
        return new CustomerOverviewPO(driver);
    }

    public CustomerOverviewPO createCustomerWithLastName(String lastName) {
        dataActions.setDefaultValues(ENTITY, "woLastName");
        actions.input(ENTITY, "lastName", lastName);
        actions.clickButton("button_save");
        return new CustomerOverviewPO(driver);
    }

    public void setCity(String city) {
        actions.input(ENTITY, ADDRESS_CITY, city);
        WebElement zipField = driver.findElement(By.id(ENTITY + "." + ADDRESS_ZIP));
        zipField.click();
    }

    public void setZip(String zip) {
        actions.input(ENTITY, ADDRESS_ZIP, zip);
        WebElement cityField = driver.findElement(By.id(ENTITY + "." + ADDRESS_CITY));
        cityField.click();
    }

    public void assertZip(String zip) {
        WaitConditions.waitForVaadin(driver);
        assertions.assertText(ENTITY, ADDRESS_ZIP, zip);
    }

    public void assertCity(String city) {
        WaitConditions.waitForVaadin(driver);
        assertions.assertText(ENTITY, ADDRESS_CITY, city);
    }

}
