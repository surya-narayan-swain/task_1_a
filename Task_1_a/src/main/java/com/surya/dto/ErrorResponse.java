package com.surya.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private long timestamp = System.currentTimeMillis();

    public ErrorResponse(String message) {
        this.message = message;
    }
}