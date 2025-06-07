package com.authorization.authserviceproject.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends  BaseModel{
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    @ManyToMany
    private List<Role> roles;
}
