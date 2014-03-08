package org.qagile.domain;

import org.apache.commons.lang3.RandomStringUtils;

public class PortalUser {


    public User createNewValidUser() {
        String password = createUniqueValidPassword();
        return new User().setEmail(createUniqueEmail()).setPassword(password).setConfirmation(password);
    }

    private String createUniqueValidPassword() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    private String createUniqueEmail() {
        return RandomStringUtils.randomAlphabetic(5).concat("@mailinator.com");
    }

    public User withUniquePasswordOfLength(int length) {
        String password = RandomStringUtils.randomAlphabetic(length);
        return new User().setEmail(createUniqueEmail()).setPassword(password).setConfirmation(password);
    }

    public User validRegisteredUser() {
        return new User().setEmail("pankaj@nakhat.com").setPassword("test1234");
    }

    public User invalidRegisteredUser() {
        return new User().setEmail("invalid@invalid.com").setPassword("invalid");
    }
}
