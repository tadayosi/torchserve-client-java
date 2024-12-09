package io.github.tadayosi.torchserve.client.model;

import io.github.tadayosi.torchserve.client.management.model.ListModels200ResponseModelsInner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Model {

    private static final Logger LOG = LoggerFactory.getLogger(Model.class);

    protected String modelName = null;
    protected String modelUrl = null;

    public Model() {
    }

    public static Model from(ListModels200ResponseModelsInner src) {
        Model model = new Model();
        model.setModelName(src.getModelName());
        model.setModelUrl(src.getModelUrl());
        return model;
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
