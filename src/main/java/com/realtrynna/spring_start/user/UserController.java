package com.realtrynna.spring_start.user;

import com.realtrynna.spring_start.board.model.Board;
import com.realtrynna.spring_start.common.exception.ApiResponse;
import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import com.realtrynna.spring_start.user.model.request.UpdateUserDto;
import com.realtrynna.spring_start.user.model.response.UserResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("create")
    public ApiResponse<UserResponseDto> create(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.create(createUserDto);

        return ApiResponse.success(new UserResponseDto(
            1L,
            createUserDto.getName(),
            createUserDto.getEmail()
        ));
    }

    @PatchMapping("update")
    public void update(@RequestBody UpdateUserDto updateUserDto) {
        userService.update(updateUserDto);
    }
}
