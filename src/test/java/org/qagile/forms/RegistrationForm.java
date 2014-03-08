package org.qagile.forms;


import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import org.qagile.domain.User;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationForm extends WebForm {


    @DefaultValue(value = "pnakhat@gmail.com")
    @Action("SET")
    @Locator(how = How.ID, using = "user_email", name = "email")
    public By email = By.id("user_email");

    @DefaultValue(value = "Password123")
    @Action("SET")
    @Locator(how = How.ID, using = "user_password", name = "password")
    public By password = By.id("user_password");

    @DefaultValue(value = "Password123")
    @Action("SET")
    @Locator(how = How.ID, using = "user_password_confirmation", name = "passwordConfirm")
    public By passwordConfirm = By.id("user_password_confirmation");

    @DefaultValue(value = "")
    @Action("CLICK")
    @Locator(how = How.CSS, using = ".button", name = "button")
    public By createButton;


    private String emailValue = null;
    private String passwordValue = null;
    private String passwordConfirmValue = null;


    public RegistrationForm withUser(User user) {
        emailValue = user.getEmail();
        passwordValue = user.getPassword().trim();
        passwordConfirmValue = user.getConfirmation().trim();
        replaceDefaultValues();
        return this;
    }


    public RegistrationForm initialize(DriverProvider driverProvider) {
        return initialize(this , driverProvider);
    }

    private void replaceDefaultValues() {
        for (WebFormFields webFormField : webFormFieldsList) {
            if (webFormField.getName().equalsIgnoreCase("email")) {
                webFormField.setDefaultvalue(emailValue);
            }

            if (webFormField.getName().equalsIgnoreCase("password")) {
                webFormField.setDefaultvalue(passwordValue);
            }

            if (webFormField.getName().equalsIgnoreCase("passwordConfirm")) {
                webFormField.setDefaultvalue(passwordConfirmValue);
            }

        }
    }
}
