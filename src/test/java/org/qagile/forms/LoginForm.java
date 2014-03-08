package org.qagile.forms;


import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import org.qagile.domain.User;
import org.qagile.selenium.DriverProvider;

public class LoginForm extends WebForm {


    @DefaultValue(value = "pnakhat@gmail.com")
    @Action("SET")
    @Locator(how = How.ID, using = "user_email", name = "email")
    public By email = By.id("user_email");

    @DefaultValue(value = "Password123")
    @Action("SET")
    @Locator(how = How.ID, using = "user_password", name = "password")
    public By password = By.id("user_password");

    @DefaultValue(value = "")
    @Action("CLICK")
    @Locator(how = How.CSS, using = "#existing-customer div form p input.button", name = "button")
    public By loginButton;


    private String emailValue = null;
    private String passwordValue = null;


    public LoginForm withUser(User user) {
        emailValue = user.getEmail();
        passwordValue = user.getPassword().trim();
        replaceDefaultValues();
        return this;
    }


    public LoginForm initialize(DriverProvider driverProvider) {
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

        }
    }
}
