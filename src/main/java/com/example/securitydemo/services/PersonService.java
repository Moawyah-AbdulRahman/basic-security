package com.example.securitydemo.services;

import com.example.securitydemo.models.Person;
import com.example.securitydemo.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public boolean personExists(String username){
        return personRepository.existsById(username);
    }

    public Person getPersonByUsername(String username) {
        return personRepository.findById(username).orElseThrow();
    }
}
