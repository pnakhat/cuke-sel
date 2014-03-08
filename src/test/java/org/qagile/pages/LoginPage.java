package org.qagile.pages;

import org.openqa.selenium.NoSuchElementException;
import org.qagile.domain.User;
import org.qagile.elements.LoginElements;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginPage implements PageStructure {

    @Autowired
    private DriverProvider driverProvider;
    @Autowired
    private LoginElements loginElements;

    @Override
    public void navigate() {
       loginElements.loginLink().click();
    }

    public boolean isUserLoggedIn() {
        try {
            return loginElements.logOutLink().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void logOut() {
        loginElements.logOutLink().click();
    }

    public void login(User user) {
        loginElements.userName().sendKeys(user.getEmail());
    }
}
