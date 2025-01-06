package com.realtrynna.spring_start.user.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
