package com.example.demo.exception;

public class GlobalNotFoundException extends RuntimeException {
    public GlobalNotFoundException(String message) {
        super(message);
    }
}
