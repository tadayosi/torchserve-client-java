package com.github.tadayosi.torchserve.client;

/**
 * Metrics API
 */
public interface Metrics {

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    String metrics() throws Exception;

    /**
     * Get TorchServe application metrics in prometheus format.
     */
    String metrics(String name) throws Exception;

}
