package com.example.fundvita.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Generic API Response wrapper to provide consistent response format
 */
@Setter
@Getter
public class ApiResponse<T> {
    // Getters and Setters
    private boolean success;
    private String message;
    private T data;
    private String errorCode;

    public ApiResponse(boolean success, String message, T data, String errorCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }

    // Success response
    public ApiResponse(boolean success, String message, T data) {
        this(success, message, data, null);
    }

    // Error response
    public ApiResponse(boolean success, String message, String errorCode) {
        this(success, message, null, errorCode);
    }

}

