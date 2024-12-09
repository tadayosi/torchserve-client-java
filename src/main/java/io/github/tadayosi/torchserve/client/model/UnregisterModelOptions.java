package io.github.tadayosi.torchserve.client.model;

public class UnregisterModelOptions {

    /**
     * Decides whether the call is synchronous or not, default: false. (optional, default to false)
     */
    private Boolean synchronous = null;
    /**
     * Waiting up to the specified wait time if necessary for a worker to complete all pending requests. Use 0 to terminate backend worker process immediately. Use -1 for wait infinitely. (optional)
     */
    private Integer timeout = null;

    private UnregisterModelOptions() {
    }

    public static UnregisterModelOptions empty() {
        return new UnregisterModelOptions();
    }

    public static Builder builder() {
        return new UnregisterModelOptions.Builder();
    }

    public Boolean getSynchronous() {
        return synchronous;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public static class Builder {

        private final UnregisterModelOptions options = new UnregisterModelOptions();

        public Builder synchronous(Boolean synchronous) {
            options.synchronous = synchronous;
            return this;
        }

        public Builder timeout(Integer timeout) {
            options.timeout = timeout;
            return this;
        }

        public UnregisterModelOptions build() {
            return options;
        }
    }
}
