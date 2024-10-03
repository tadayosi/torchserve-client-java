package com.github.tadayosi.torchserve.client.model;

import java.util.List;
import java.util.stream.Collectors;

import com.github.tadayosi.torchserve.client.management.model.ListModels200ResponseModelsInner;
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

    public static List<Model> from(List<ListModels200ResponseModelsInner> src) {
        return src.stream().map(Model::from).collect(Collectors.toList());
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
