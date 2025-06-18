package com.spring.ecommerce.User_Service.repository;

import com.spring.ecommerce.User_Service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
