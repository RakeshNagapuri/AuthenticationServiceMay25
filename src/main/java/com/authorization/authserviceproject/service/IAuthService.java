package com.authorization.authserviceproject.service;

import com.authorization.authserviceproject.models.Token;
import com.authorization.authserviceproject.models.User;

public interface IAuthService {
    User signup(String username, String aEmail, String aPassword, String aPhoneNumber);
    Token login(String aEmail, String aPassword);
}
