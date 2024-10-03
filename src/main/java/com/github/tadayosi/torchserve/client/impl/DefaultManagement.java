package com.github.tadayosi.torchserve.client.impl;

import java.util.List;

import com.github.tadayosi.torchserve.client.Management;
import com.github.tadayosi.torchserve.client.management.api.DefaultApi;
import com.github.tadayosi.torchserve.client.management.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.Api;
import com.github.tadayosi.torchserve.client.model.ApiException;
import com.github.tadayosi.torchserve.client.model.ModelDetail;
import com.github.tadayosi.torchserve.client.model.ModelList;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.Response;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

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
        } catch (RestClientException e) {
            throw new ApiException("Operation registerModel failed", e);
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
        } catch (RestClientException e) {
            throw new ApiException("Operation setAutoScale failed", e);
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
        } catch (RestClientException e) {
            throw new ApiException("Operation setAutoScale failed", e);
        }
    }

    @Override
    public List<ModelDetail> describeModel(String modelName) throws ApiException {
        try {
            return ModelDetail.from(api.describeModel(modelName));
        } catch (RestClientException e) {
            throw new ApiException("Operation describeModel failed", e);
        }
    }

    @Override
    public List<ModelDetail> describeModel(String modelName, String modelVersion) throws ApiException {
        try {
            return ModelDetail.from(api.versionDescribeModel(modelName, modelVersion));
        } catch (RestClientException e) {
            throw new ApiException("Operation describeModel failed", e);
        }
    }

    @Override
    public Response unregisterModel(String modelName, UnregisterModelOptions options) throws ApiException {
        try {
            return Response.from(api.unregisterModel(modelName,
                options.getSynchronous(),
                options.getTimeout()));
        } catch (RestClientException e) {
            throw new ApiException("Operation unregisterModel failed", e);
        }
    }

    @Override
    public Response unregisterModel(String modelName, String modelVersion, UnregisterModelOptions options)
        throws ApiException {
        try {
            return Response.from(api.versionUnregisterModel(modelName, modelVersion,
                options.getSynchronous(),
                options.getTimeout()));
        } catch (RestClientException e) {
            throw new ApiException("Operation unregisterModel failed", e);
        }
    }

    @Override
    public ModelList listModels(Integer limit, String nextPageToken) throws ApiException {
        try {
            return ModelList.from(api.listModels(limit, nextPageToken));
        } catch (RestClientException e) {
            throw new ApiException("Operation listModels failed", e);
        }
    }

    @Override
    public Response setDefault(String modelName, String modelVersion) throws ApiException {
        try {
            return Response.from(api.setDefault(modelName, modelVersion));
        } catch (RestClientException e) {
            throw new ApiException("Operation setDefault failed", e);
        }
    }

    @Override
    public Api apiDescription() throws ApiException {
        try {
            return Api.from(api.apiDescription());
        } catch (RestClientException e) {
            throw new ApiException("Operation apiDescription failed", e);
        }
    }

    @Override
    public Object token(String type) throws ApiException {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
