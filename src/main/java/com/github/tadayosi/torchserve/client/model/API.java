package com.github.tadayosi.torchserve.client.model;

import java.util.HashMap;
import java.util.Map;

public class API {

    private String openapi = null;
    private Map<String, String> info = new HashMap<>();
    private Map<String, Object> paths = new HashMap<>();

    public API() {
    }

    @SuppressWarnings("unchecked")
    public static API from(com.github.tadayosi.torchserve.client.inference.model.InlineResponse200 src) {
        API api = new API();
        api.setOpenapi(src.getOpenapi());
        api.setInfo((Map<String, String>) src.getInfo());
        api.setPaths((Map<String, Object>) src.getPaths());
        return api;
    }

    @SuppressWarnings("unchecked")
    public static API from(com.github.tadayosi.torchserve.client.management.model.InlineResponse200 src) {
        API api = new API();
        api.setOpenapi(src.getOpenapi());
        api.setInfo((Map<String, String>) src.getInfo());
        api.setPaths((Map<String, Object>) src.getPaths());
        return api;
    }

    public String getOpenapi() {
        return openapi;
    }

    public void setOpenapi(String openapi) {
        this.openapi = openapi;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public Map<String, Object> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Object> paths) {
        this.paths = paths;
    }
}
