package com.authorization.authserviceproject.service;

import com.authorization.authserviceproject.exceptions.UserAlreadyExistException;
import com.authorization.authserviceproject.exceptions.UserNotSignedException;
import com.authorization.authserviceproject.models.Token;
import com.authorization.authserviceproject.models.User;
import com.authorization.authserviceproject.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    private TokenRepo tokenRepo;

    @Autowired
    public AuthService(UserRepo userRepo, TokenRepo tokenRepo, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }


    @Override
    public User signup(String username, String aEmail, String aPassword, String aPhoneNumber) {

        Optional<User> userOptional = userRepo.findByEmailEquals(aEmail);
        if(userOptional.isPresent()){
            throw new UserAlreadyExistException("User already exists, please login instead.");
        }
        User user = new User();
        user.setEmail(aEmail);
        user.setName(username);
        user.setPhoneNumber(aPhoneNumber);
        user.setPassword(bcryptPasswordEncoder.encode(aPassword));
        return userRepo.save(user);
    }

    @Override
    public Token login(String aEmail, String aPassword) {
        Optional<User> userOptional = userRepo.findByEmailEquals(aEmail);
        if(userOptional.isEmpty()){
            throw new UserNotSignedException("User not signed in.");
        }
        User user = userOptional.get();
        if(!bcryptPasswordEncoder.matches(aPassword, user.getPassword())){
            throw new UserNotSignedException("Invalid password.");
        }
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,30);
        token.setExpiresAt(calendar.getTime());

        return tokenRepo.save(token);
    }
}
