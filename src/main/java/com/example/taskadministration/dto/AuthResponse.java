package com.example.taskadministration.dto;

public class AuthResponse {

    private String accessToken;
//    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
