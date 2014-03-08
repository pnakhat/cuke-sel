package org.qagile.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

public class Pages {

    @Autowired
    private HomePage homePage;

    @Autowired
    private RegistrationPage registrationPage;

    @Autowired
    private LoginPage loginPage;

    public HomePage homePage() {
        return homePage;
    }

    public RegistrationPage registrationPage() {
        return registrationPage;
    }

    public LoginPage loginPage() {
        return  loginPage;
    }


}
