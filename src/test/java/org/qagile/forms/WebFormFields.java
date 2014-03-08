package org.qagile.forms;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebFormFields {

    private WebElement webElement;
    private String defaultvalue;
    private String action;
    private String name;

    public WebElement getWebElement() {
        return webElement;
    }

    public WebFormFields setWebElement(WebElement webElement) {
        this.webElement = webElement;
        return this;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public WebFormFields setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
        return this;
    }

    public String getAction() {
        return action;
    }

    public WebFormFields setAction(String action) {
        this.action = action;
        return this;
    }

    public void takeDefaultAction() {
        if (action.equalsIgnoreCase("CLICK")) {
            webElement.click();
        }

        if (action.equalsIgnoreCase("SELECT")) {
            Select select = new Select(webElement);
            select.selectByVisibleText(defaultvalue);
        }

        if (action.equalsIgnoreCase("SET")) {
            webElement.sendKeys(defaultvalue);
        }

    }

    public String getName() {
        return name;
    }

    public WebFormFields setName(String name) {
        this.name = name;
        return this;
    }
}
