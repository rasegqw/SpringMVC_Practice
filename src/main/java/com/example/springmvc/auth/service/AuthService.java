package com.example.springmvc.auth.service;

import com.example.springmvc.auth.dto.LoginRequest;
import com.example.springmvc.auth.dto.LoginResponse;
import com.example.springmvc.auth.exception.AuthException;
import com.example.springmvc.user.domain.User;
import com.example.springmvc.user.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public LoginResponse login(LoginRequest request) throws AuthException {

        User user = userRepository
                .findByUserId(request.getUserId())
                .orElseThrow(() -> new AuthException("존재하지 않는 사용자"));

        if (!user.matchPassword(request.getPassword())) {
            throw new AuthException("비밀번호 불일치");
        }

        return LoginResponse.from(user);
    }
}
