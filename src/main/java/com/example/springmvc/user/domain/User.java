package com.example.springmvc.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.Date;
import java.util.function.Supplier;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Getter
    Long id;

    @Getter
    String userId;

    @Getter
    String password;

    @Getter
    String nickname;

    @GeneratedValue
    @Getter
    Date createdAt;

    public User(Long id, String userId, String password, String nickname, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    private User(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    public static User of(String user1, String password, String nick) {
        return new User(user1, password, nick);
    }

    public static User create(String userId, String password, String nickname) {
        return new User(userId, password, nickname);
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

}
