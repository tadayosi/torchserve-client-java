package com.github.tadayosi.torchserve.client;

/**
 * Metrics API
 */
public interface Metrics {

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    Object metrics() throws Exception;

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    Object metrics(String name) throws Exception;

}
