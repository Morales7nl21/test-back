package com.test.technique.library.exception;


public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}