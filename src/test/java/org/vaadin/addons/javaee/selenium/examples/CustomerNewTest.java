package org.vaadin.addons.javaee.selenium.examples;

import org.junit.Ignore;
import org.junit.Test;
import org.vaadin.addons.javaee.selenium.SeleniumTest;

public class CustomerNewTest extends SeleniumTest {

    @Test
    @Ignore
    public void createNewCustomer() {
        CustomerNewPO customerNewPO = navigateToCustomerNew();
        CustomerOverviewPO overviewPO = customerNewPO.createCustomer();
        overviewPO.assertCustomer();
    }

    @Test
    @Ignore
    public void populateZipByCity() {
        CustomerNewPO customerNewPO = navigateToCustomerNew();
        customerNewPO.setCity("Munich");
        customerNewPO.assertZip("80000");
    }

    @Test
    @Ignore
    public void populateCityByZip() {
        CustomerNewPO customerNewPO = navigateToCustomerNew();
        customerNewPO.setZip("80000");
        customerNewPO.assertCity("Munich");
    }

    private CustomerNewPO navigateToCustomerNew() {
        // Dummie
        return null;
    }

}
