package com.example.springmvc.user.controller;

import com.example.springmvc.user.domain.User;
import com.example.springmvc.user.dto.UserCreateRequest;
import com.example.springmvc.user.dto.UserCreateResponse;
import com.example.springmvc.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserContollerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Test
    void successCreateUser() throws Exception {

        User user = User.of("asdf", "1234", "nick");
        UserCreateResponse response = UserCreateResponse.from(user);

        given(userService.create(any())).willReturn(response);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "userId" : "asdf",
                        "password" : "1234",
                        "nickname" : "nick"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("asdf"))
                .andExpect(jsonPath("$.nickname").value("nick"));
    }

    @Test
    void failCreateUser() throws Exception {

        UserCreateRequest request = new UserCreateRequest("asdf", "1234", "nick");
        given(userService.create(any()))
                .willThrow(new IllegalArgumentException("이미 존재하는 아이디입니다."));


        assertThatThrownBy(()->userService.create(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 아이디입니다.");
    }
}
