package com.github.tadayosi.torchserve.client.model;

public class Response {

    private String status;

    public Response() {
    }

    public static Response from(com.github.tadayosi.torchserve.client.inference.model.InlineResponse2001 src) {
        Response response = new Response();
        response.setStatus(src.getStatus());
        return response;
    }

    public static Response from(com.github.tadayosi.torchserve.client.management.model.InlineResponse2002 src) {
        Response response = new Response();
        response.setStatus(src.getStatus());
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
            "    status: " + status + "\n" +
            "}";
    }
}
