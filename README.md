Selenium addon for Vaadin 
==============

Selenium utilities to test Vaadin applications.

It supports an abstraction layer to easily read / write and assert value inside fields. Currently supported fields are:
- CheckBox
- Date
- DropDown
- OptionGroup
- Text
- TextArea
To use it, use the InputMethodFactory to get a InputMethod for the elemenent you want to access.

It supports automatically filling / validation of forms with values from a property file (see DataDriven* classes).
It has an utility to handle the often occuring StaleElementReferenceException (SaveElementAccess)
It contains a class WaitConditions to wait for vaadin background ajax calls to finsh or to wait for specific conditions.
Its base SeleniumTest class has a driver enhancement to take a screenshot when a test fails.
The base Page Object (BasePO) has all these utils initialized and bound together.

It is configured by a property file named env.properties:
url: The url of the main page of your vaadin app to test
remote: (TRUE / FALSE) If you use a selenium server, set this to true. It will then use the remote driver
seleniumGrid: The URL of the selenium server
SAUCE_ONDEMAND_ACCESS_KEY: If testing with sauce labs, this holds the access key
SAUCE_ONDEMAND_USERNAME: If testing with sauce labs, this holds the username

Additionally it supports the ConfirmDialog from the vaadin addon with an own ConfirmDialogPO. 


Vaadin 7.1 is currently not supported, there are problems with the Date field. See message https://vaadin.com/forum#!/thread/3689227.