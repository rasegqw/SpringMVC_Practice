package com.example.springmvc.auth.dto;

import com.example.springmvc.auth.exception.AuthException;
import com.example.springmvc.user.domain.User;
import lombok.Getter;

// 올바른 response 형태 -> 엔티티를 그대로 담지 말 것.
public class LoginResponse {

    @Getter
    private Long id;
    @Getter
    private String userId;
    @Getter
    private String nickname;

    private LoginResponse(Long id, String userId, String nickname) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
    }

    public static LoginResponse from(User user) {
        return new LoginResponse(user.getId(), user.getUserId(), user.getNickname());
    }

    public static LoginResponse of(Long id, String userId, String nickname) {
        return new LoginResponse(id, userId, nickname);
    }

    public void isInstanceOf(Class<AuthException> authExceptionClass) {

    }
}
