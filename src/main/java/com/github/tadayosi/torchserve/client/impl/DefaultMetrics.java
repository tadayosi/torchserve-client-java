package com.github.tadayosi.torchserve.client.impl;

import com.github.tadayosi.torchserve.client.Metrics;
import com.github.tadayosi.torchserve.client.metrics.api.DefaultApi;
import com.github.tadayosi.torchserve.client.metrics.invoker.ApiClient;

public class DefaultMetrics implements Metrics {

    private final DefaultApi api;

    public DefaultMetrics() {
        this(8082);
    }

    public DefaultMetrics(int port) {
        ApiClient client = new ApiClient().setBasePath("http://localhost:" + port);
        this.api = new DefaultApi(client);
    }

    @Override
    public String metrics() throws Exception {
        return metrics(null);
    }

    @Override
    public String metrics(String name) throws Exception {
        return api.metrics(name);
    }

}
