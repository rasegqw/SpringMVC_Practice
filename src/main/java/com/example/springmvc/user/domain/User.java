package com.example.springmvc.user.domain;

import lombok.Getter;

import java.util.Date;
import java.util.function.Supplier;

public class User {

    @Getter
    Long id;
    @Getter
    String userId;
    @Getter
    String password;
    @Getter
    String nickname;
    @Getter
    Date createdAt;

    public User(Long id, String userId, String password, String nickname, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public User(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    public static User of(String user1, String password, String nick) {
        return new User(user1, password, nick);
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

}
