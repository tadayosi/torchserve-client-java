package com.github.tadayosi.torchserve.client.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Model {

    private static final Logger LOG = LoggerFactory.getLogger(Model.class);

    protected String modelName = null;
    protected String modelUrl = null;

    public Model() {
    }

    public static Model fromMap(Object src) {
        if (!(src instanceof Map)) {
            LOG.error("Unexpected model data: {}", src);
            return new Model();
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) src;
        Model model = new Model();
        model.setModelName(map.get("modelName"));
        model.setModelUrl(map.get("modelUrl"));
        return model;
    }

    public static List<Model> fromMap(List<Object> src) {
        return src.stream().map(Model::fromMap).collect(Collectors.toList());
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
            "    modelName: " + modelName + "\n" +
            "    modelUrl: " + modelUrl + "\n" +
            "}";
    }
}
