package com.example.springmvc.auth.service;

import com.example.springmvc.auth.dto.LoginRequest;
import com.example.springmvc.auth.dto.LoginResponse;
import com.example.springmvc.auth.exception.AuthException;
import com.example.springmvc.user.domain.User;
import com.example.springmvc.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthService authService;

    @Test
    void loginSuccess() {
        // given
        LoginRequest request = new LoginRequest("user1", "1234");
        User user = User.of("user1", "1234", "nick");

        given(userRepository.findByUserId("user1")).willReturn(Optional.of(user));

        // when
        LoginResponse response = authService.login(request);

        // then
        assertThat(response.getUserId()).isEqualTo("user1");
    }

    @Test
    void loginFail() {
        // given
        LoginRequest request = new LoginRequest("user1", "wrong");
        User user = User.of("user1", "1234", "nick");

        given(userRepository.findByUserId("user1")).willReturn(Optional.of(user));

        // when / then
        assertThatThrownBy(() -> authService.login(request)).isInstanceOf(AuthException.class);

    }
}