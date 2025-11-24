package com.example.demospringboot.model;

// Ini adalah model/DTO yang mewakili data yang diterima saat login
public class LoginRequest {
    private String username;
    private String password;

    // Getters dan Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}