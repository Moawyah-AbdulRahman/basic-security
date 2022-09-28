package com.example.securitydemo.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    private String firstName;
    private String lastName;
}
