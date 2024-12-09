package io.github.tadayosi.torchserve.client.model;

import java.util.HashMap;
import java.util.Map;

public class Api {

    private String openapi = null;
    private Map<String, String> info = new HashMap<>();
    private Map<String, Object> paths = new HashMap<>();

    public Api() {
    }

    @SuppressWarnings("unchecked")
    public static Api from(io.github.tadayosi.torchserve.client.inference.model.ApiDescription200Response src) {
        Api api = new Api();
        api.setOpenapi(src.getOpenapi());
        api.setInfo((Map<String, String>) src.getInfo());
        api.setPaths((Map<String, Object>) src.getPaths());
        return api;
    }

    @SuppressWarnings("unchecked")
    public static Api from(io.github.tadayosi.torchserve.client.management.model.ApiDescription200Response src) {
        Api api = new Api();
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
            "    openapi: " + openapi + "\n" +
            "    info: " + info + "\n" +
            "    paths: " + paths + "\n" +
            "}";
    }
}
