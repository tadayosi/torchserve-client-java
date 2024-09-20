package com.github.tadayosi.torchserve.client.impl;

import com.github.tadayosi.torchserve.client.Inference;
import com.github.tadayosi.torchserve.client.inference.api.DefaultApi;
import com.github.tadayosi.torchserve.client.inference.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.Api;
import com.github.tadayosi.torchserve.client.model.ApiException;
import com.github.tadayosi.torchserve.client.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultInference implements Inference {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultInference.class);

    private final DefaultApi api;

    public DefaultInference() {
        this(8080);
    }

    public DefaultInference(int port) {
        this("http://localhost:" + port);
    }

    public DefaultInference(String address) {
        ApiClient client = new ApiClient().setBasePath(address);
        this.api = new DefaultApi(client);
        LOG.info("Inference API address: {}", address);
    }

    public void setAuthToken(String token) {
        api.getApiClient().addDefaultHeader("Authorization", "Bearer " + token);
    }

    @Override
    public Api apiDescription() throws ApiException {
        try {
            return Api.from(api.apiDescription());
        } catch (com.github.tadayosi.torchserve.client.inference.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response ping() throws ApiException {
        try {
            return Response.from(api.ping());
        } catch (com.github.tadayosi.torchserve.client.inference.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Object predictions(String modelName, Object body) throws ApiException {
        try {
            return api.predictions(body, modelName);
        } catch (com.github.tadayosi.torchserve.client.inference.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Object predictions(String modelName, String modelVersion, Object body) throws ApiException {
        try {
            return api.versionPredictions(body, modelName, modelVersion);
        } catch (com.github.tadayosi.torchserve.client.inference.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Object explanations(String modelName) {
        throw new UnsupportedOperationException("Not supported yet");
    }

}
