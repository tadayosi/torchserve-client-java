package com.github.tadayosi.torchserve.client.model;

import java.util.ArrayList;
import java.util.List;

import com.github.tadayosi.torchserve.client.management.model.InlineResponse2001;

public class ModelList {

    private String nextPageToken = null;
    private List<Model> models = new ArrayList<>();

    public ModelList() {
    }

    public static ModelList from(InlineResponse2001 inlineResponse2001) {
        ModelList modelList = new ModelList();
        modelList.setNextPageToken(inlineResponse2001.getNextPageToken());
        modelList.setModels(Model.fromMap(inlineResponse2001.getModels()));
        return modelList;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
            "    nextPageToken: " + nextPageToken + "\n" +
            "    models: " + models + "\n" +
            "}";
    }
}
