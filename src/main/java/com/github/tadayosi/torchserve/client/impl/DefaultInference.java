package com.github.tadayosi.torchserve.client.impl;

import com.github.tadayosi.torchserve.client.Inference;
import com.github.tadayosi.torchserve.client.inference.api.DefaultApi;
import com.github.tadayosi.torchserve.client.inference.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.API;
import com.github.tadayosi.torchserve.client.model.Response;

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
    public API apiDescription() throws Exception {
        return API.from(api.apiDescription());
    }

    @Override
    public Response ping() throws Exception {
        return Response.from(api.ping());
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
