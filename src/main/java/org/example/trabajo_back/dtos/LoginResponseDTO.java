package org.example.trabajo_back.dtos;

import java.util.List;

public class LoginResponseDTO {
    private String token;
    private List<String> roles;
    private String username;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String username, List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
