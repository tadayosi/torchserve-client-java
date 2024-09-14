package com.github.tadayosi.torchserve.client.model;

public class RegisterModelOptions {

    /**
     * Name of model. This value will override modelName in MANIFEST.json if present. (optional)
     */
    private String modelName = null;
    /**
     * Inference handler entry-point. This value will override handler in MANIFEST.json if present. (optional)
     */
    private String handler = null;
    /**
     * Runtime for the model custom service code. This value will override runtime in MANIFEST.json if present. (optional)
     */
    private String runtime = null;
    /**
     * Inference batch size, default: 1. (optional)
     */
    private Integer batchSize = null;
    /**
     * Maximum delay for batch aggregation, default: 100. (optional)
     */
    private Integer maxBatchDelay = null;
    /**
     * Maximum time, in seconds, the TorchServe waits for a response from the model inference code, default: 120. (optional)
     */
    private Integer responseTimeout = null;
    /**
     * Number of initial workers, default: 0. (optional)
     */
    private Integer initialWorkers = null;
    /**
     * Decides whether creation of worker synchronous or not, default: false. (optional, default to false)
     */
    private Boolean synchronous = null;
    /**
     * Model mar file is S3 SSE KMS(server side encryption) enabled or not, default: false. (optional, default to false)
     */
    private Boolean s3SseKms = null;

    private RegisterModelOptions() {
    }

    public static RegisterModelOptions empty() {
        return new RegisterModelOptions();
    }

    public static Builder builder() {
        return new RegisterModelOptions.Builder();
    }

    public String getModelName() {
        return modelName;
    }

    public String getHandler() {
        return handler;
    }

    public String getRuntime() {
        return runtime;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public Integer getMaxBatchDelay() {
        return maxBatchDelay;
    }

    public Integer getResponseTimeout() {
        return responseTimeout;
    }

    public Integer getInitialWorkers() {
        return initialWorkers;
    }

    public Boolean getSynchronous() {
        return synchronous;
    }

    public Boolean getS3SseKms() {
        return s3SseKms;
    }

    public static class Builder {

        private final RegisterModelOptions options = new RegisterModelOptions();

        public Builder modelName(String modelName) {
            options.modelName = modelName;
            return this;
        }

        public Builder handler(String handler) {
            options.handler = handler;
            return this;
        }

        public Builder runtime(String runtime) {
            options.runtime = runtime;
            return this;
        }

        public Builder batchSize(Integer batchSize) {
            options.batchSize = batchSize;
            return this;
        }

        public Builder maxBatchDelay(Integer maxBatchDelay) {
            options.maxBatchDelay = maxBatchDelay;
            return this;
        }

        public Builder responseTimeout(Integer responseTimeout) {
            options.responseTimeout = responseTimeout;
            return this;
        }

        public Builder initialWorkers(Integer initialWorkers) {
            options.initialWorkers = initialWorkers;
            return this;
        }

        public Builder synchronous(Boolean synchronous) {
            options.synchronous = synchronous;
            return this;
        }

        public Builder s3SseKms(Boolean s3SseKms) {
            options.s3SseKms = s3SseKms;
            return this;
        }

        public RegisterModelOptions build() {
            return options;
        }
    }
}
