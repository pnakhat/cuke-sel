package org.qagile.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginElements {

    @Autowired
    private DriverProvider driverProvider;

    public WebElement loginLink() {
        return driverProvider.findElement(By.cssSelector("#link-to-login a:nth-child(1)"));
    }

    public WebElement userName() {
        return driverProvider.findElement(By.id("user_email"));
    }

    public WebElement password() {
        return driverProvider.findElement(By.id("user_password"));
    }

    public WebElement loginButton() {
        return driverProvider.findElement(By.cssSelector(".button"));
    }

    public WebElement logOutLink() {
        return driverProvider.findElement(By.linkText("LOGOUT"), 3);
    }
}
