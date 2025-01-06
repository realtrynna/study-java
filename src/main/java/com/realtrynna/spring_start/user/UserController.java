package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("sign-up")
    public String signUp(@RequestBody CreateUserDto createUserDto) {
        System.out.println(createUserDto);

        return "here";
    }
}
