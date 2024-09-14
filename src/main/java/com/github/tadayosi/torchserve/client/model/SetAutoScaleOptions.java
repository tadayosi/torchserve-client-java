package com.github.tadayosi.torchserve.client.model;

public class SetAutoScaleOptions {

    /**
     * Minimum number of worker processes. (optional)
     */
    private Integer minWorker = null;
    /**
     * Maximum number of worker processes. (optional)
     */
    private Integer maxWorker = null;
    /**
     * Number of GPU worker processes to create. (optional)
     */
    private Integer numberGpu = null;
    /**
     * Decides whether the call is synchronous or not, default: false. (optional, default to false)
     */
    private Boolean synchronous = null;
    /**
     * Waiting up to the specified wait time if necessary for a worker to complete all pending requests. Use 0 to terminate backend worker process immediately. Use -1 for wait infinitely. (optional)
     */
    private Integer timeout = null;

    private SetAutoScaleOptions() {
    }

    public static SetAutoScaleOptions empty() {
        return new SetAutoScaleOptions();
    }

    public static Builder builder() {
        return new SetAutoScaleOptions.Builder();
    }

    public Integer getMinWorker() {
        return minWorker;
    }

    public Integer getMaxWorker() {
        return maxWorker;
    }

    public Integer getNumberGpu() {
        return numberGpu;
    }

    public Boolean getSynchronous() {
        return synchronous;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public static class Builder {

        private final SetAutoScaleOptions options = new SetAutoScaleOptions();

        public Builder minWorker(Integer minWorker) {
            options.minWorker = minWorker;
            return this;
        }

        public Builder maxWorker(Integer maxWorker) {
            options.maxWorker = maxWorker;
            return this;
        }

        public Builder numberGpu(Integer numberGpu) {
            options.numberGpu = numberGpu;
            return this;
        }

        public Builder synchronous(Boolean synchronous) {
            options.synchronous = synchronous;
            return this;
        }

        public Builder timeout(Integer timeout) {
            options.timeout = timeout;
            return this;
        }

        public SetAutoScaleOptions build() {
            return options;
        }
    }
}
