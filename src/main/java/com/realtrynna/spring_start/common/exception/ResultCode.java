package com.realtrynna.spring_start.common.exception;

public enum ResultCode {
    SUCCESS("성공");

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
