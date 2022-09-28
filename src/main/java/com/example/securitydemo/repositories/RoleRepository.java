package com.example.securitydemo.repositories;

import com.example.securitydemo.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,String> {
}
