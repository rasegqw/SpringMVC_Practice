package com.example.springmvc.auth.controller;

import com.example.springmvc.auth.dto.LoginResponse;
import com.example.springmvc.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AuthService authService;

    @Test
    void loginSuccessAPI() throws Exception {
        // given
        LoginResponse response = LoginResponse.of(1L, "user1", "nick");
        given(authService.login(any())).willReturn(response);

        // when / then
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "userId": "user1",
                            "password": "1234"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user1"));
    }
}