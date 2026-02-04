package com.example.springmvc.auth.controller;


import com.example.springmvc.auth.dto.LoginRequest;
import com.example.springmvc.auth.dto.LoginResponse;
import com.example.springmvc.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// RestController -> 이 클래스가 HTTP 요청을 처리하는 Controller 임을 알 수 있음.
// 내부적으로 @Controller + @ResponseBody(반환값을 View가 아닌 HTTP ResponseBody로 사용) 합친 것.
// 즉, Response로 반환하겠다 이말임. -> JSON으로 변환되어 클라이언트에 응답함.
@RequestMapping("/auth")
// 이 컨트롤러가 처리하는 URL의 공통 prefix
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
            // @RequestBody: HTTP Request Body(JSON)을 Java 객체로 변환
    ) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}