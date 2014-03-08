package org.qagile.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;
import junit.framework.Assert;
import org.qagile.domain.Errors;
import org.qagile.domain.PortalUser;
import org.qagile.domain.User;
import org.qagile.forms.LoginForm;
import org.qagile.forms.RegistrationForm;
import org.qagile.pages.Pages;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class MyStepDefs {

    @Autowired
    private Pages pages;

    private List<User> users;

    private User user;

    @Autowired
    private DriverProvider driverProvider;


    @Given("^I navigate to spree portal$")
    public void I_navigate_to_spree_portal() throws Throwable {
        pages.homePage().navigate();
        pages.loginPage().navigate();
    }


    @When("^I have some user waiting to be registered")
    public void I_setup_some_users(List<User> users) throws Throwable {
        this.users = users;
    }

    @When("^I register the user with the given detail$")
    public void I_register_the_user_with_the_given_detail() throws Throwable {
        registerNewUser(users.get(0));
        users = null;

    }

    private void registerNewUser(User user) {
        pages.loginPage().navigate();
        pages.registrationPage().navigate();
        new RegistrationForm().initialize(driverProvider)
                .withUser(user)
                .fillForm();
    }

    @Then("^users should be registered successfully$")
    public void users_should_be_registered() throws Throwable {
        Assert.assertEquals(pages.registrationPage().getRegisteredMessage(), "Welcome! You have signed up successfully.");
    }

    @Given("^I have a valid user details$")
    public void I_have_a_valid_user_details() throws Throwable {
        user = new PortalUser().createNewValidUser();
    }

    @When("^I register the user$")
    public void I_register_the_user() throws Throwable {
        registerNewUser(user);
    }

    @Given("^I am a user and I choose less then six character long password$")
    public void I_am_a_user_and_I_choose_less_then_six_character_long_password() throws Throwable {
        user = new PortalUser().withUniquePasswordOfLength(4);
    }

    @Then("^user should not be registered$")
    public void user_should_not_be_registered() throws Throwable {
        assertTrue(pages.registrationPage().isDisplayed());
    }

    @And("^I should be told that password is less the six characters long$")
    public void I_should_be_told_that_password_is_less_the_six_characters_long() throws Throwable {

        List<String> errorMessagesList = pages.registrationPage().getErrorMessagesList();
        for (String error : errorMessagesList) {
            if (error.equals(Errors.PASSWORD_SHOULD_BE_SIX_CHAR.getErrorText())) {
                assertTrue(true);
                return;
            }
        }
            fail("Could not find error message ");
    }

    @After
    public void tearDownScenarion() {
        if (pages.loginPage().isUserLoggedIn()) {
            pages.loginPage().logOut();
        }
    }

    @Given("^I am a valid registered user$")
    public void I_am_a_valid_registered_user() throws Throwable {
        user = new PortalUser().validRegisteredUser();

    }

    @When("^I login to system$")
    public void I_login_to_system() throws Throwable {
        pages.loginPage()
                .navigate();
        new LoginForm().initialize(driverProvider).withUser(user).fillForm();
    }

    @Then("^I '(.+)' logged in$")
    public void I_should_be_logged_in(String shouldLoggedIn) throws Throwable {
        if (shouldLoggedIn.equals("should be")) {
            assertTrue(pages.loginPage().isUserLoggedIn());
        } else if (shouldLoggedIn.equals("should not be")) {
            assertFalse(pages.loginPage().isUserLoggedIn());
        }
    }

    @Given("^I am an invalid user$")
    public void I_am_an_invalid_user() throws Throwable {
        user = new PortalUser().invalidRegisteredUser();
    }
}
