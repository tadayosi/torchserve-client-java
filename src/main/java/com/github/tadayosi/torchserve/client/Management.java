package com.github.tadayosi.torchserve.client;

import java.util.List;

import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;

/**
 * Management API
 */
public interface Management {

    /**
     * Register a new model in TorchServe.
     */
    Object registerModel(String url, RegisterModelOptions options) throws Exception;

    /**
     * Configure number of workers for a default version of a model. This is an asynchronous call by default. Caller need to call describeModel to check if the model workers has been changed.
     */
    Object setAutoScale(String modelName, SetAutoScaleOptions options) throws Exception;

    /**
     * Configure number of workers for a specified version of a model. This is an asynchronous call by default. Caller need to call describeModel to check if the model workers has been changed.
     */
    Object setAutoScale(String modelName, String modelVersion, SetAutoScaleOptions options) throws Exception;

    /**
     * Provides detailed information about the default version of a model.
     */
    List<Object> describeModel(String modelName) throws Exception;

    /**
     * Provides detailed information about the specified version of a model.If "all" is specified as version, returns the details about all the versions of the model.
     */
    List<Object> describeModel(String modelName, String modelVersion) throws Exception;

    /**
     * Unregister the default version of a model from TorchServe if it is the only version available. This is an asynchronous call by default. Caller can call listModels to confirm model is unregistered.
     */
    Object unregisterModel(String modelName, UnregisterModelOptions options) throws Exception;

    /**
     * Unregister the specified version of a model from TorchServe. This is an asynchronous call by default. Caller can call listModels to confirm model is unregistered.
     */
    Object unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options) throws Exception;

    /**
     * List registered models in TorchServe.
     */
    Object listModels(Integer limit, String nextPageToken) throws Exception;

    /**
     * Set default version of a model.
     */
    Object setDefault(String modelName, String modelVersion) throws Exception;

    /**
     * Get openapi description.
     */
    Object apiDescription() throws Exception;

    /**
     * Not supported yet.
     */
    Object token(String type) throws Exception;

}
