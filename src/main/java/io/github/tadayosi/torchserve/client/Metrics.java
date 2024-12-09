package io.github.tadayosi.torchserve.client;

import io.github.tadayosi.torchserve.client.model.ApiException;

/**
 * Metrics API
 */
public interface Metrics {

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    String metrics() throws ApiException;

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    String metrics(String name) throws ApiException;

}
