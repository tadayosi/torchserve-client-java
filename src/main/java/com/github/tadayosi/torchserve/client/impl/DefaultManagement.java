package com.github.tadayosi.torchserve.client.impl;

import java.util.List;

import com.github.tadayosi.torchserve.client.Management;
import com.github.tadayosi.torchserve.client.management.api.DefaultApi;
import com.github.tadayosi.torchserve.client.management.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;

public class DefaultManagement implements Management {

    private final DefaultApi api;

    public DefaultManagement() {
        this(8081);
    }

    public DefaultManagement(int port) {
        ApiClient client = new ApiClient().setBasePath("http://localhost:" + port);
        this.api = new DefaultApi(client);
    }

    @Override
    public Object registerModel(String url, RegisterModelOptions options) throws Exception {
        return api.registerModel(url, null,
            options.getModelName(),
            options.getHandler(),
            options.getRuntime(),
            options.getBatchSize(),
            options.getMaxBatchDelay(),
            options.getResponseTimeout(),
            options.getInitialWorkers(),
            options.getSynchronous(),
            options.getS3SseKms());
    }

    @Override
    public Object setAutoScale(String modelName, SetAutoScaleOptions options) throws Exception {
        return api.setAutoScale(modelName,
            options.getMinWorker(),
            options.getMaxWorker(),
            options.getNumberGpu(),
            options.getSynchronous(),
            options.getTimeout());
    }

    @Override
    public Object setAutoScale(String modelName, String modelVersion, SetAutoScaleOptions options) throws Exception {
        return api.versionSetAutoScale(modelName, modelVersion,
            options.getMinWorker(),
            options.getMaxWorker(),
            options.getNumberGpu(),
            options.getSynchronous(),
            options.getTimeout());
    }

    @Override
    public List<Object> describeModel(String modelName) throws Exception {
        return List.copyOf(api.describeModel(modelName));
    }

    @Override
    public List<Object> describeModel(String modelName, String modelVersion) throws Exception {
        return List.copyOf(api.versionDescribeModel(modelName, modelVersion));
    }

    @Override
    public Object unregisterModel(String modelName, UnregisterModelOptions options) throws Exception {
        return api.unregisterModel(modelName,
            options.getSynchronous(),
            options.getTimeout());
    }

    @Override
    public Object unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options) throws Exception {
        return api.versionUnregisterModel(modelName, modelVersion,
            options.getSynchronous(),
            options.getTimeout());
    }

    @Override
    public Object listModels(Integer limit, String nextPageToken) throws Exception {
        return api.listModels(limit, nextPageToken);
    }

    @Override
    public Object setDefault(String modelName, String modelVersion) throws Exception {
        return api.setDefault(modelName, modelVersion);
    }

    @Override
    public Object apiDescription() throws Exception {
        return api.apiDescription();
    }

    @Override
    public Object token(String type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet");
    }

}
