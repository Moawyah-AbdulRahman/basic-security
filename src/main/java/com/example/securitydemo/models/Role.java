package com.example.securitydemo.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Role {
    @Id
    private String name;
}
