package com.authorization.authserviceproject.dtos;

import com.authorization.authserviceproject.models.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private List<Role> roles = new ArrayList<>();
}
