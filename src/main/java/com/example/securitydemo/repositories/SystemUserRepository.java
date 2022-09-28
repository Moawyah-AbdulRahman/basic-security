package com.example.securitydemo.repositories;

import com.example.securitydemo.models.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface SystemUserRepository extends CrudRepository<SystemUser, String> {
}
