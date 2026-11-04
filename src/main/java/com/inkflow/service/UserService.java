package com.inkflow.service;

import com.inkflow.dto.RegisterRequest;
import com.inkflow.entity.User;
import com.inkflow.enums.Role;
import com.inkflow.exception.EmailAlreadyExistsException;
import com.inkflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerUser(RegisterRequest request) {

        if (emailExists(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Role role;
        try {
            role = Role.valueOf(request.getRole().trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException | NullPointerException exception) {
            throw new IllegalArgumentException("Invalid registration role");
        }
        if (role != Role.AUTHOR && role != Role.READER) {
            throw new IllegalArgumentException("Invalid registration role");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
