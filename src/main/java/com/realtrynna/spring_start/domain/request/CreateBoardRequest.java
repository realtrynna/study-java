package com.realtrynna.spring_start.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBoardRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private String imageUrl;
}
