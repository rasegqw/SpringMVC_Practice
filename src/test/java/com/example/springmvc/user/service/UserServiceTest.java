package com.example.springmvc.user.service;


import com.example.springmvc.user.domain.User;
import com.example.springmvc.user.dto.UserCreateRequest;
import com.example.springmvc.user.dto.UserCreateResponse;
import com.example.springmvc.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void successUserCreate() {

        // given -> UserRequest 필요, UserRepository.existsByUserId 응답 필요
        User user = User.of("asdf", "1234", "nick");
        UserCreateRequest request = new UserCreateRequest("asdf", "1234", "nick");

        given(userRepository.existsByUserId("asdf")).willReturn(false);
        given(userRepository.save(any(User.class))).willReturn(user);

        // then
        UserCreateResponse response = userService.create(request);
        assertThat(response.getUserId()).isEqualTo("asdf");
        assertThat(response.getNickname()).isEqualTo("nick");
    }

}
