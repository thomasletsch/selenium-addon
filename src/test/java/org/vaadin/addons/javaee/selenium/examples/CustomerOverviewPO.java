package org.vaadin.addons.javaee.selenium.examples;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vaadin.addons.javaee.selenium.po.BasePO;

public class CustomerOverviewPO extends BasePO {

    public static final String ID_ELEMENT = "CustomerOverviewPanel";

    public CustomerOverviewPO(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getIdentifyingElement() {
        return ID_ELEMENT;
    }

    public List<WebElement> getCustomerActivities() {
        List<WebElement> customers = reads.getTableRows("CustomerActivityOptimizedTable");
        return customers;
    }

    public void clickDeleteButtonInFirstRow() {
        actions.clickTableButtonWithConfirmation("CustomerActivityOptimizedTable", 1, 4);
    }

    public void assertCustomer() {
        dataAssertions.assertDefaultValues("Customer");
    }

    public void assertCustomer(String lastName) {
        assertions.assertNoError();
        assertions.assertText("Customer", "lastName", lastName);
        dataAssertions.assertDefaultValues("Customer", "woLastName");
    }

    public void deactivateCustomer() {
        actions.clickButton("button_deactivate");
    }

    public void setCustomerLastName(String lastName) {
        actions.input("Customer", "lastName", lastName);
    }

    public void clickSaveButton() {
        actions.clickButton("button_save");
    }

}
