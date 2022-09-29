package com.example.securitydemo.controllers;

import com.example.securitydemo.models.SystemUser;
import com.example.securitydemo.security.UserService;
import com.example.securitydemo.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@AllArgsConstructor
public class SecurityController {
    private final PersonService personService;
    private final UserService userService;

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

    @PostMapping("/admin")
    public ResponseEntity aloha(@RequestBody SystemUser systemUser) {
        if (userService.userExists(systemUser.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        userService.createSystemUser(systemUser);
        return ResponseEntity.created(URI.create("user/" + systemUser.getUsername())).build();
    }

}
