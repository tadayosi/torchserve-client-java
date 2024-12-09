package io.github.tadayosi.torchserve.client.model;

import java.util.List;
import java.util.Map;

public class ApiException extends Exception {

    private int code = 0;
    private Map<String, List<String>> responseHeaders = null;
    private String responseBody = null;

    public ApiException(io.github.tadayosi.torchserve.client.inference.invoker.ApiException e) {
        super(e.getResponseBody(), e);
        this.code = e.getCode();
        this.responseHeaders = e.getResponseHeaders();
        this.responseBody = e.getResponseBody();
    }

    public ApiException(io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
        super(e.getResponseBody(), e);
        this.code = e.getCode();
        this.responseHeaders = e.getResponseHeaders();
        this.responseBody = e.getResponseBody();
    }

    public ApiException(io.github.tadayosi.torchserve.client.metrics.invoker.ApiException e) {
        super(e.getResponseBody(), e);
        this.code = e.getCode();
        this.responseHeaders = e.getResponseHeaders();
        this.responseBody = e.getResponseBody();
    }

    public int getCode() {
        return code;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
