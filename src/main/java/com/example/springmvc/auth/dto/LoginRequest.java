package com.example.springmvc.auth.dto;


import lombok.Getter;
import lombok.Setter;

public class LoginRequest {

    @Getter
    String userId;
    @Getter
    String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
