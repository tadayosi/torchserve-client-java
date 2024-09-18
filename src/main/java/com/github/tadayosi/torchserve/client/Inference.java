package com.github.tadayosi.torchserve.client;

import com.github.tadayosi.torchserve.client.model.API;
import com.github.tadayosi.torchserve.client.model.Response;

/**
 * Inference API
 */
public interface Inference {

    /**
     * Get openapi description.
     */
    API apiDescription() throws Exception;

    /**
     * Get TorchServe status.
     */
    Response ping() throws Exception;

    /**
     * Predictions entry point to get inference using default model version.
     */
    Object predictions(String modelName, Object body) throws Exception;

    /**
     * Predictions entry point to get inference using specific model version.
     */
    Object predictions(String modelName, String modelVersion, Object body) throws Exception;

    /**
     * Not supported yet.
     */
    Object explanations(String modelName) throws Exception;

}
