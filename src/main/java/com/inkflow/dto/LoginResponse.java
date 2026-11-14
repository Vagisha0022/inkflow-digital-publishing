
package com.inkflow.dto;

import com.inkflow.enums.Role;

public class LoginResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String email;
    private Role role;

    public LoginResponse(String token, Long userId, String email, Role role) {
        this.token = token;
        this.tokenType = "Bearer";
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
