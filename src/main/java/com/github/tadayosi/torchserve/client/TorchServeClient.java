package com.github.tadayosi.torchserve.client;

import com.github.tadayosi.torchserve.client.impl.DefaultInference;
import com.github.tadayosi.torchserve.client.impl.DefaultManagement;
import com.github.tadayosi.torchserve.client.impl.DefaultMetrics;

public class TorchServeClient {

    private final Inference inference;
    private final Management management;
    private final Metrics metrics;

    private TorchServeClient() {
        this(new DefaultInference(), new DefaultManagement(), new DefaultMetrics());
    }

    private TorchServeClient(Inference inference, Management management, Metrics metrics) {
        this.inference = inference;
        this.management = management;
        this.metrics = metrics;
    }

    public static TorchServeClient newInstance() {
        return new TorchServeClient();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Inference inference() {
        return inference;
    }

    public Management management() {
        return management;
    }

    public Metrics metrics() {
        return metrics;
    }

    public static class Builder {

        private Integer inferencePort;
        private Integer managementPort;
        private Integer metricsPort;

        public Builder inferencePort(int port) {
            this.inferencePort = port;
            return this;
        }

        public Builder managementPort(int port) {
            this.managementPort = port;
            return this;
        }

        public Builder metricsPort(Integer metricsPort) {
            this.metricsPort = metricsPort;
            return this;
        }

        public TorchServeClient build() {
            Inference inference = inferencePort == null ? new DefaultInference() : new DefaultInference(inferencePort);
            Management management = managementPort == null ? new DefaultManagement() : new DefaultManagement(managementPort);
            Metrics metrics = metricsPort == null ? new DefaultMetrics() : new DefaultMetrics(metricsPort);
            return new TorchServeClient(inference, management, metrics);
        }
    }
}
