package com.github.tadayosi.torchserve.client.impl;

import com.github.tadayosi.torchserve.client.Metrics;
import com.github.tadayosi.torchserve.client.metrics.api.DefaultApi;
import com.github.tadayosi.torchserve.client.metrics.invoker.ApiClient;
import com.github.tadayosi.torchserve.client.model.ApiException;

public class DefaultMetrics implements Metrics {

    private final DefaultApi api;

    public DefaultMetrics() {
        this(8082);
    }

    public DefaultMetrics(int port) {
        this("http://localhost:" + port);
    }

    public DefaultMetrics(String address) {
        ApiClient client = new ApiClient().setBasePath(address);
        this.api = new DefaultApi(client);
    }

    @Override
    public String metrics() throws ApiException {
        return metrics(null);
    }

    @Override
    public String metrics(String name) throws ApiException {
        try {
            return api.metrics(name);
        } catch (com.github.tadayosi.torchserve.client.metrics.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

}
