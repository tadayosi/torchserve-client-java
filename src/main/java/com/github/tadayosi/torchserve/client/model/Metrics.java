package com.github.tadayosi.torchserve.client.model;

import com.github.tadayosi.torchserve.client.management.model.ModelsmodelNameMetrics;
import com.google.gson.annotations.SerializedName;

public class Metrics {

    private Integer rejectedRequests = null;
    private Integer waitingQueueSize = null;
    private Integer requests = null;

    public Metrics() {
    }

    public static Metrics from(ModelsmodelNameMetrics src) {
        if (src == null) {
            return null;
        }

        Metrics metrics = new Metrics();
        metrics.setRejectedRequests(src.getRejectedRequests());
        metrics.setWaitingQueueSize(src.getWaitingQueueSize());
        metrics.setRequests(src.getRequests());
        return metrics;
    }

    public Integer getRejectedRequests() {
        return rejectedRequests;
    }

    public void setRejectedRequests(Integer rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public Integer getWaitingQueueSize() {
        return waitingQueueSize;
    }

    public void setWaitingQueueSize(Integer waitingQueueSize) {
        this.waitingQueueSize = waitingQueueSize;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
            " rejectedRequests: " + rejectedRequests + "," +
            " waitingQueueSize: " + waitingQueueSize + "," +
            " requests: " + requests + " " +
            "}";
    }
}
