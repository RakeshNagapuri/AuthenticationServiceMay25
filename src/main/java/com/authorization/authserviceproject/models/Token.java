package com.authorization.authserviceproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@Entity
public class Token extends  BaseModel{
    private String value;
    private Date expiresAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
