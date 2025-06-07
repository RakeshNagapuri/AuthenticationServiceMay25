package com.authorization.authserviceproject.controller;

import com.authorization.authserviceproject.dtos.LoginRequestDTO;
import com.authorization.authserviceproject.dtos.SignupRequestDTO;
import com.authorization.authserviceproject.dtos.UserDTO;
import com.authorization.authserviceproject.models.Token;
import com.authorization.authserviceproject.models.User;
import com.authorization.authserviceproject.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody SignupRequestDTO aRequestDTO) {
        User user = this.authService.signup(aRequestDTO.getName(),
                aRequestDTO.getEmail(),
                aRequestDTO.getPassword(),
                aRequestDTO.getPhoneNumber());

        return from(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO aLoginRequestDTO) {
        Token token = this.authService.login(aLoginRequestDTO.getEmail(),aLoginRequestDTO.getPassword());
        return new ResponseEntity<>(token.getValue(), HttpStatus.OK);
    }

    @GetMapping("/validate/{aToken}")
    public void validateToken(@PathVariable String aToken) {

    }


    public UserDTO from(User aUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(aUser.getId());
        userDTO.setName(aUser.getName());
        userDTO.setEmail(aUser.getEmail());
        userDTO.setRoles(aUser.getRoles());
        return userDTO;
    }

}
