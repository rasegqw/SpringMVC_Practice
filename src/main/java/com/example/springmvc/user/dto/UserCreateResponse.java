package com.example.springmvc.user.dto;

import com.example.springmvc.user.domain.User;
import lombok.Getter;

@Getter
public class UserCreateResponse {

    private final String userId;
    private final String nickname;

    private UserCreateResponse(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(user.getUserId(), user.getNickname());
    }
}
