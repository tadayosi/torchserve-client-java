package com.github.tadayosi.torchserve.client.model;

import org.springframework.web.client.RestClientException;

public class ApiException extends Exception {

    public ApiException(String message, RestClientException cause) {
        super(message, cause);
    }
}
