package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean findByEmail(String email)  {
        return userRepository.findByEmail(email).isPresent();
    }

    public void create(CreateUserDto createUserDto) {
        Boolean isUser = findByEmail(createUserDto.getEmail());

//        if (isUser)
    }
}
