package io.github.tadayosi.torchserve.client.impl;

import io.github.tadayosi.torchserve.client.Metrics;
import io.github.tadayosi.torchserve.client.metrics.api.DefaultApi;
import io.github.tadayosi.torchserve.client.metrics.invoker.ApiClient;
import io.github.tadayosi.torchserve.client.model.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMetrics implements Metrics {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetrics.class);

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
        LOG.info("Metrics API address: {}", address);
    }

    @Override
    public String metrics() throws ApiException {
        return metrics(null);
    }

    @Override
    public String metrics(String name) throws ApiException {
        try {
            return api.metrics(name);
        } catch (io.github.tadayosi.torchserve.client.metrics.invoker.ApiException e) {
            throw new ApiException(e);
        }
    }

}
