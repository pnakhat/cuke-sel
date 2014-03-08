package org.qagile.pages;


import org.openqa.selenium.WebElement;
import org.qagile.domain.User;
import org.qagile.elements.RegistrationPageElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationPage implements PageStructure {

    @Autowired
    private RegistrationPageElements registrationPageElements;


    @Override
    public void navigate() {
        registrationPageElements.registrationLink().click();
    }

    public boolean register(User user) {
        return false;
    }

    public String getRegisteredMessage() {
        return registrationPageElements.flashMessage().isEnabled()?registrationPageElements.flashMessage().getText():null;
    }

    public List<String> getErrorMessagesList() {
        List<String> errors = new ArrayList<String>();
        for(WebElement errorElement: registrationPageElements.errorList()) {
            errors.add(errorElement.getText());
        }
        return errors;
    }

    public boolean isDisplayed() {
        return registrationPageElements.pageCheckElement().isDisplayed();
    }
}
