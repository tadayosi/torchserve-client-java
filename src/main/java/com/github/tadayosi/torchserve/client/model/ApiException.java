package com.github.tadayosi.torchserve.client.model;

public class ApiException extends Exception {

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
