package com.example.springmvc.user.service;

import com.example.springmvc.user.domain.User;
import com.example.springmvc.user.dto.UserCreateRequest;
import com.example.springmvc.user.dto.UserCreateResponse;
import com.example.springmvc.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 항상 불변성을 위해, 접근 제어자로 변경 막기
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse create(UserCreateRequest request) {

        // 중복 체크하고,
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 엔티티 생성하고,
        User user = User.create(request.getUserId(), request.getPassword(), request.getNickname());

        // 엔티티 레포지에 저장.
         User saveUser = userRepository.save(user);

         return UserCreateResponse.from(saveUser);
    }
}
