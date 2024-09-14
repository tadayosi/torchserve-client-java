package com.github.tadayosi.torchserve.client.impl;

import com.github.tadayosi.torchserve.client.Inference;
import com.github.tadayosi.torchserve.client.inference.api.DefaultApi;
import com.github.tadayosi.torchserve.client.inference.invoker.ApiClient;

public class DefaultInference implements Inference {

    private final DefaultApi api;

    public DefaultInference() {
        this(8080);
    }

    public DefaultInference(int port) {
        ApiClient client = new ApiClient().setBasePath("http://localhost:" + port);
        this.api = new DefaultApi(client);
    }

    @Override
    public Object apiDescription() throws Exception {
        return api.apiDescription();
    }

    @Override
    public Object ping() throws Exception {
        return api.ping();
    }

    @Override
    public Object predictions(String modelName, Object body) throws Exception {
        return api.predictions(body, modelName);
    }

    @Override
    public Object predictions(String modelName, String modelVersion, Object body) throws Exception {
        return api.versionPredictions(body, modelName, modelVersion);
    }

    @Override
    public Object explanations(String modelName) {
        throw new UnsupportedOperationException("Not supported yet");
    }

}
