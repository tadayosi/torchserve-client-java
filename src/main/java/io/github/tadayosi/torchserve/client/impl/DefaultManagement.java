package io.github.tadayosi.torchserve.client.impl;

import java.util.List;
import java.util.Map;

import io.github.tadayosi.torchserve.client.Management;
import io.github.tadayosi.torchserve.client.management.api.DefaultApi;
import io.github.tadayosi.torchserve.client.management.invoker.ApiClient;
import io.github.tadayosi.torchserve.client.management.model.DescribeModel200ResponseInner;
import io.github.tadayosi.torchserve.client.model.Api;
import io.github.tadayosi.torchserve.client.model.ApiException;
import io.github.tadayosi.torchserve.client.model.ModelDetail;
import io.github.tadayosi.torchserve.client.model.ModelList;
import io.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import io.github.tadayosi.torchserve.client.model.Response;
import io.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import io.github.tadayosi.torchserve.client.model.UnregisterModelOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultManagement implements Management {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultManagement.class);

    private final DefaultApi api;

    public DefaultManagement() {
        this(8081);
    }

    public DefaultManagement(int port) {
        this("http://localhost:" + port);
    }

    public DefaultManagement(String address) {
        ApiClient client = new ApiClient().setBasePath(address);
        this.api = new DefaultApi(client);
        LOG.info("Management API address: {}", address);
    }

    public void setAuthToken(String token) {
        api.getApiClient().addDefaultHeader("Authorization", "Bearer " + token);
    }

    @Override
    public Response registerModel(String url, RegisterModelOptions options) throws ApiException {
        try {
            return Response.from(api.registerModel(url,
                options.getModelName(),
                options.getHandler(),
                options.getRuntime(),
                options.getBatchSize(),
                options.getMaxBatchDelay(),
                options.getResponseTimeout(),
                options.getInitialWorkers(),
                options.getSynchronous(),
                options.getS3SseKms(),
                null));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response setAutoScale(String modelName, SetAutoScaleOptions options) throws ApiException {
        try {
            return Response.from(api.setAutoScale(modelName,
                options.getMinWorker(),
                options.getMaxWorker(),
                options.getNumberGpu(),
                options.getSynchronous(),
                options.getTimeout()));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response setAutoScale(String modelName, String modelVersion, SetAutoScaleOptions options) throws ApiException {
        try {
            return Response.from(api.versionSetAutoScale(modelName, modelVersion,
                options.getMinWorker(),
                options.getMaxWorker(),
                options.getNumberGpu(),
                options.getSynchronous(),
                options.getTimeout()));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public List<ModelDetail> describeModel(String modelName) throws ApiException {
        try {
            List<DescribeModel200ResponseInner> response = api.describeModel(modelName);
            return response.stream().map(ModelDetail::from).toList();
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public List<ModelDetail> describeModel(String modelName, String modelVersion) throws ApiException {
        try {
            List<DescribeModel200ResponseInner> response = api.versionDescribeModel(modelName, modelVersion);
            return response.stream().map(ModelDetail::from).toList();
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response unregisterModel(String modelName, UnregisterModelOptions options) throws ApiException {
        try {
            return Response.from(api.unregisterModel(modelName,
                options.getSynchronous(),
                options.getTimeout()));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options)
        throws ApiException {
        try {
            return Response.from(api.versionUnregisterModel(modelName, modelVersion,
                options.getSynchronous(),
                options.getTimeout()));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public ModelList listModels(Integer limit, String nextPageToken) throws ApiException {
        try {
            return ModelList.from(api.listModels(limit, nextPageToken));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Response setDefault(String modelName, String modelVersion) throws ApiException {
        try {
            return Response.from(api.setDefault(modelName, modelVersion));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Api apiDescription() throws ApiException {
        try {
            // Workaround for HTTPClient 5.4 requiring content-type for OPTIONS requests
            return Api.from(api.apiDescription(Map.of("Content-Type", "application/json")));
        } catch (io.github.tadayosi.torchserve.client.management.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

    @Override
    public Object token(String type) throws ApiException {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
