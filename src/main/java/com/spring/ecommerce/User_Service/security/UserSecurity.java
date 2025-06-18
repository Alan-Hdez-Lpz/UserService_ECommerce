package com.spring.ecommerce.User_Service.security;

import com.spring.ecommerce.User_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    @Autowired
    private UserRepository userRepository;

    public boolean isSelf(Authentication authentication, Long userId) {
        String username = authentication.getName();
        return userRepository.findById(userId)
                .map(user -> user.getUserName().equals(username))
                .orElse(false);
    }
}
