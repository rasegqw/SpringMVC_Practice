package com.example.springmvc.user.controller;

import com.example.springmvc.user.dto.UserCreateRequest;
import com.example.springmvc.user.dto.UserCreateResponse;
import com.example.springmvc.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
// 항상 스프링에서 컨트롤러를 사용할 땐, @RestController를 통해 컨트롤러임을 명시해줘야 함.
// 왜? ->
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    // 항상 REST 설계에선 행위에 대한 정보는 URL에서 뺌.
    // -> POST 로 동작을 설명.
    public ResponseEntity<UserCreateResponse> createUser(
            @RequestBody UserCreateRequest request
    ) {
        UserCreateResponse response = userService.create(request);
        return ResponseEntity.ok(response);
    }
}
