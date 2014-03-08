package org.qagile.domain;

/**
 * Created with IntelliJ IDEA.
 * User: pankajnakhat
 * Date: 04/07/2013
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public enum Errors {

    PASSWORD_SHOULD_BE_SIX_CHAR("Password is too short (minimum is 6 characters)");
    private String errorText;

    Errors(String errorText) {
        this.errorText = errorText;
    }

    public static String getByName(String error) {
        for(Errors errors : Errors.values()) {
            if(errors.name().equals(error)) {
                return errors.errorText;
            }
        }
        throw new RuntimeException("Could not find enum " + error);
    }

    public String getErrorText() {
        return errorText;
    }



}
