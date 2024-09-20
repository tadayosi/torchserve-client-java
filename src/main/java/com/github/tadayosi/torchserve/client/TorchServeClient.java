package com.github.tadayosi.torchserve.client;

import java.util.Optional;

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

        private final Configuration configuration = Configuration.load();

        private Optional<String> inferenceKey = configuration.getInferenceKey();
        private Optional<String> inferenceAddress = configuration.getInferenceAddress();
        private Optional<Integer> inferencePort = configuration.getInferencePort();

        private Optional<String> managementKey = configuration.getManagementKey();
        private Optional<String> managementAddress = configuration.getManagementAddress();
        private Optional<Integer> managementPort = configuration.getManagementPort();

        private Optional<String> metricsAddress = configuration.getMetricsAddress();
        private Optional<Integer> metricsPort = configuration.getMetricsPort();

        public Builder inferenceKey(String key) {
            this.inferenceKey = Optional.of(key);
            return this;
        }

        public Builder inferenceAddress(String address) {
            this.inferenceAddress = Optional.of(address);
            return this;
        }

        public Builder inferencePort(int port) {
            this.inferencePort = Optional.of(port);
            return this;
        }

        public Builder managementKey(String key) {
            this.managementKey = Optional.of(key);
            return this;
        }

        public Builder managementAddress(String address) {
            this.managementAddress = Optional.of(address);
            return this;
        }

        public Builder managementPort(int port) {
            this.managementPort = Optional.of(port);
            return this;
        }

        public Builder metricsAddress(String address) {
            this.metricsAddress = Optional.of(address);
            return this;
        }

        public Builder metricsPort(Integer port) {
            this.metricsPort = Optional.of(port);
            return this;
        }

        public TorchServeClient build() {
            DefaultInference inference = inferenceAddress.map(DefaultInference::new)
                .orElse(inferencePort.map(DefaultInference::new)
                    .orElse(new DefaultInference()));
            inferenceKey.ifPresent(inference::setAuthToken);

            DefaultManagement management = managementAddress.map(DefaultManagement::new)
                .orElse(managementPort.map(DefaultManagement::new)
                    .orElse(new DefaultManagement()));
            managementKey.ifPresent(management::setAuthToken);

            DefaultMetrics metrics = metricsAddress.map(DefaultMetrics::new)
                .orElse(metricsPort.map(DefaultMetrics::new)
                    .orElse(new DefaultMetrics()));
            return new TorchServeClient(inference, management, metrics);
        }
    }
}
