package com.github.tadayosi.torchserve.client;

/**
 * Inference API
 */
public interface Inference {

    /**
     * Get openapi description.
     */
    Object apiDescription() throws Exception;

    /**
     * Get TorchServe status.
     */
    Object ping() throws Exception;

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
