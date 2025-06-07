package com.authorization.authserviceproject.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String roleName;
}
