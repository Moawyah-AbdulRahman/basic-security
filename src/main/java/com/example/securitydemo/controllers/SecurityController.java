package com.example.securitydemo.controllers;

import com.example.securitydemo.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class SecurityController {
    private final PersonService personService;

    @GetMapping("admin/{username}")
    public ResponseEntity hi(@PathVariable("username") String username) {
        return personService.personExists(username) ?
                ResponseEntity.ok(personService.getPersonByUsername(username)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("{username}")
    public ResponseEntity hello(@PathVariable("username") String username) {
        return personService.personExists(username) ?
                ResponseEntity.ok(username) :
                ResponseEntity.notFound().build();
    }
}
