package org.qagile.domain;

public class User {
    private String email;
    private String password;
    private String confirmation;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public User setConfirmation(String confirmation) {
        this.confirmation = confirmation;
        return this;

    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
