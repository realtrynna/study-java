package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean existUser(String email)  {
        return userRepository.existsByEmail(email);
    }

    public void create(CreateUserDto createUserDto) {
        Boolean isUser = existUser(createUserDto.getEmail());

        if (isUser) {
            /**
             * 예외 처리
             */
        }
    }
}
