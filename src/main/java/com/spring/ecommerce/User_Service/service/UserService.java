package com.spring.ecommerce.User_Service.service;

import com.spring.ecommerce.User_Service.model.*;
import com.spring.ecommerce.User_Service.repository.RoleRepository;
import com.spring.ecommerce.User_Service.repository.UserRepository;
import com.spring.ecommerce.User_Service.security.JwtUtil;
import com.spring.ecommerce.User_Service.security.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordUtil passwordUtil;

    public User register(User newUser) {
        if (userRepository.findByUserName(newUser.getUserName()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        Role role = roleRepository.findByName(newUser.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(passwordUtil.hashPassword(newUser.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }

    public String authenticate(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
            return jwtUtil.generateToken(authentication.getName());
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}