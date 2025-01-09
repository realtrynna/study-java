package com.realtrynna.spring_start.common.exception;

import java.util.Optional;
import lombok.Getter;

@Getter()
public class ApiResponse<T> {
    private final ResultCode code;
    private final String message;
    private final Optional<T> data;

    private ApiResponse(ResultCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = Optional.ofNullable(data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
            ResultCode.SUCCESS,
            ResultCode.SUCCESS.getMessage(),
            data
        );
    }
}
