package com.authorization.authserviceproject.exceptions;

public class UserNotSignedException extends RuntimeException {
    public UserNotSignedException(String message) {
        super(message);
    }
}
