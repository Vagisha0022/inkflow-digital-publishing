
package com.inkflow.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @GetMapping("/dashboard")
    public Map<String, String> getAuthorDashboard(
            Authentication authentication) {

        return Map.of(
                "message", "Welcome to the Author Dashboard!",
                "email", authentication.getName(),
                "role", authentication.getAuthorities().toString()
        );
    }
}
