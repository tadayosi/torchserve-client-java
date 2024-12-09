package io.github.tadayosi.torchserve.client;

import io.github.tadayosi.torchserve.client.model.Api;
import io.github.tadayosi.torchserve.client.model.ApiException;
import io.github.tadayosi.torchserve.client.model.Response;

/**
 * Inference API
 */
public interface Inference {

    /**
     * Get openapi description.
     */
    Api apiDescription() throws ApiException;

    /**
     * Get TorchServe status.
     */
    Response ping() throws ApiException;

    /**
     * Predictions entry point to get inference using default model version.
     */
    Object predictions(String modelName, Object body) throws ApiException;

    /**
     * Predictions entry point to get inference using specific model version.
     */
    Object predictions(String modelName, String modelVersion, Object body) throws ApiException;

    /**
     * Not supported yet.
     */
    Object explanations(String modelName) throws ApiException;

}
