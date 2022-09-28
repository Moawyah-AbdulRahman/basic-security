package com.example.securitydemo.repositories;

import com.example.securitydemo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
