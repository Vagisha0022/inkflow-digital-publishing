
package com.inkflow.controller;

import com.inkflow.dto.RegisterRequest;
import com.inkflow.dto.LoginRequest;
import com.inkflow.dto.LoginResponse;
import com.inkflow.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User Registration
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        userService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully"));
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.authenticateUser(request);

        return ResponseEntity.ok(response);
    }

    // Get authenticated user's profile
    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
                Map.of(
                        "email", authentication.getName(),
                        "message", "You are authenticated!"
                )
        );
    }
}
