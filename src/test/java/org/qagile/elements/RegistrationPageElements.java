package org.qagile.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationPageElements {

    @Autowired
    private DriverProvider driverProvider;

    public WebElement registrationLink() {
         return driverProvider.findElement(By.linkText("Create a new account"));
    }

    public WebElement flashMessage() {
        return driverProvider.findElement(By.cssSelector("#content .flash"));
    }

    public List<WebElement> errorList() {
        return driverProvider.findElements(By.xpath("//div[@id='errorExplanation']//li"));
    }

    public WebElement pageCheckElement() {
        return driverProvider.findElement(By.id("signup"));
    }
}
