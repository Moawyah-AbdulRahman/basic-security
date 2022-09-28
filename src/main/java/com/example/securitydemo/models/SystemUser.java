package com.example.securitydemo.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
public class SystemUser {
    @Id
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
