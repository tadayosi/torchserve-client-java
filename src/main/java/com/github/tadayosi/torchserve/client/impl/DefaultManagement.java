package com.github.tadayosi.torchserve.client.impl;

import java.util.List;
import java.util.Map;

import com.github.tadayosi.torchserve.client.Management;
import com.github.tadayosi.torchserve.client.management.api.DefaultApi;
import com.github.tadayosi.torchserve.client.management.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.API;
import com.github.tadayosi.torchserve.client.model.Model;
import com.github.tadayosi.torchserve.client.model.ModelDetail;
import com.github.tadayosi.torchserve.client.model.ModelList;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.Response;
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
    public Response registerModel(String url, RegisterModelOptions options) throws Exception {
        return Response.from(api.registerModel(url, null,
            options.getModelName(),
            options.getHandler(),
            options.getRuntime(),
            options.getBatchSize(),
            options.getMaxBatchDelay(),
            options.getResponseTimeout(),
            options.getInitialWorkers(),
            options.getSynchronous(),
            options.getS3SseKms()));
    }

    @Override
    public Response setAutoScale(String modelName, SetAutoScaleOptions options) throws Exception {
        return Response.from(api.setAutoScale(modelName,
            options.getMinWorker(),
            options.getMaxWorker(),
            options.getNumberGpu(),
            options.getSynchronous(),
            options.getTimeout()));
    }

    @Override
    public Response setAutoScale(String modelName, String modelVersion, SetAutoScaleOptions options) throws Exception {
        return Response.from(api.versionSetAutoScale(modelName, modelVersion,
            options.getMinWorker(),
            options.getMaxWorker(),
            options.getNumberGpu(),
            options.getSynchronous(),
            options.getTimeout()));
    }

    @Override
    public List<ModelDetail> describeModel(String modelName) throws Exception {
        return ModelDetail.from(api.describeModel(modelName));
    }

    @Override
    public List<ModelDetail> describeModel(String modelName, String modelVersion) throws Exception {
        return ModelDetail.from(api.versionDescribeModel(modelName, modelVersion));
    }

    @Override
    public Response unregisterModel(String modelName, UnregisterModelOptions options) throws Exception {
        return Response.from(api.unregisterModel(modelName,
            options.getSynchronous(),
            options.getTimeout()));
    }

    @Override
    public Response unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options)
        throws Exception {
        return Response.from(api.versionUnregisterModel(modelName, modelVersion,
            options.getSynchronous(),
            options.getTimeout()));
    }

    @Override
    public ModelList listModels(Integer limit, String nextPageToken) throws Exception {
        return ModelList.from(api.listModels(limit, nextPageToken));
    }

    @Override
    public Response setDefault(String modelName, String modelVersion) throws Exception {
        return Response.from(api.setDefault(modelName, modelVersion));
    }

    @Override
    public API apiDescription() throws Exception {
        return API.from(api.apiDescription());
    }

    @Override
    public Object token(String type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet");
    }

}
