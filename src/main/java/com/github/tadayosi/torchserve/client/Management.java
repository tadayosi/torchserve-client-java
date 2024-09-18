package com.github.tadayosi.torchserve.client;

import java.util.List;

import com.github.tadayosi.torchserve.client.model.Api;
import com.github.tadayosi.torchserve.client.model.ApiException;
import com.github.tadayosi.torchserve.client.model.ModelDetail;
import com.github.tadayosi.torchserve.client.model.ModelList;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.Response;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;

/**
 * Management API
 */
public interface Management {

    /**
     * Register a new model in TorchServe.
     */
    Response registerModel(String url, RegisterModelOptions options) throws ApiException;

    /**
     * Configure number of workers for a default version of a model. This is an asynchronous call by default. Caller need to call describeModel to check if the model workers has been changed.
     */
    Response setAutoScale(String modelName, SetAutoScaleOptions options) throws ApiException;

    /**
     * Configure number of workers for a specified version of a model. This is an asynchronous call by default. Caller need to call describeModel to check if the model workers has been changed.
     */
    Response setAutoScale(String modelName, String modelVersion, SetAutoScaleOptions options) throws ApiException;

    /**
     * Provides detailed information about the default version of a model.
     */
    List<ModelDetail> describeModel(String modelName) throws ApiException;

    /**
     * Provides detailed information about the specified version of a model.If "all" is specified as version, returns the details about all the versions of the model.
     */
    List<ModelDetail> describeModel(String modelName, String modelVersion) throws ApiException;

    /**
     * Unregister the default version of a model from TorchServe if it is the only version available. This is an asynchronous call by default. Caller can call listModels to confirm model is unregistered.
     */
    Response unregisterModel(String modelName, UnregisterModelOptions options) throws ApiException;

    /**
     * Unregister the specified version of a model from TorchServe. This is an asynchronous call by default. Caller can call listModels to confirm model is unregistered.
     */
    Response unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options) throws ApiException;

    /**
     * List registered models in TorchServe.
     */
    ModelList listModels(Integer limit, String nextPageToken) throws ApiException;

    /**
     * Set default version of a model.
     */
    Response setDefault(String modelName, String modelVersion) throws ApiException;

    /**
     * Get openapi description.
     */
    Api apiDescription() throws ApiException;

    /**
     * Not supported yet.
     */
    Object token(String type) throws ApiException;

}
