package io.github.tadayosi.torchserve.client.model;

import java.util.ArrayList;
import java.util.List;

import io.github.tadayosi.torchserve.client.management.model.DescribeModel200ResponseInner;

public class ModelDetail extends Model {

    private String modelVersion = null;
    private Integer minWorkers = null;
    private Integer maxWorkers = null;
    private Integer batchSize = null;
    private Integer maxBatchDelay = null;
    private String status = null;
    private List<Worker> workers = new ArrayList<>();
    private Metrics metrics = null;
    private JobQueueStatus jobQueueStatus = null;

    public ModelDetail() {
    }

    public static ModelDetail from(DescribeModel200ResponseInner src) {
        ModelDetail model = new ModelDetail();
        model.setModelName(src.getModelName());
        model.setModelVersion(src.getModelVersion());
        model.setModelUrl(src.getModelUrl());
        model.setMinWorkers(src.getMinWorkers());
        model.setMaxWorkers(src.getMaxWorkers());
        model.setBatchSize(src.getBatchSize());
        model.setMaxBatchDelay(src.getMaxBatchDelay());
        model.setStatus(src.getStatus());
        model.setWorkers(src.getWorkers().stream().map(Worker::from).toList());
        model.setMetrics(Metrics.from(src.getMetrics()));
        model.setJobQueueStatus(JobQueueStatus.from(src.getJobQueueStatus()));
        return model;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public Integer getMinWorkers() {
        return minWorkers;
    }

    public void setMinWorkers(Integer minWorkers) {
        this.minWorkers = minWorkers;
    }

    public Integer getMaxWorkers() {
        return maxWorkers;
    }

    public void setMaxWorkers(Integer maxWorkers) {
        this.maxWorkers = maxWorkers;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getMaxBatchDelay() {
        return maxBatchDelay;
    }

    public void setMaxBatchDelay(Integer maxBatchDelay) {
        this.maxBatchDelay = maxBatchDelay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public JobQueueStatus getJobQueueStatus() {
        return jobQueueStatus;
    }

    public void setJobQueueStatus(JobQueueStatus jobQueueStatus) {
        this.jobQueueStatus = jobQueueStatus;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
            "    modelName: " + modelName + "\n" +
            "    modelVersion: " + modelVersion + "\n" +
            "    modelUrl: " + modelUrl + "\n" +
            "    minWorkers: " + minWorkers + "\n" +
            "    maxWorkers: " + maxWorkers + "\n" +
            "    batchSize: " + batchSize + "\n" +
            "    maxBatchDelay: " + maxBatchDelay + "\n" +
            "    status: " + status + "\n" +
            "    workers: " + workers + "\n" +
            "    metrics: " + metrics + "\n" +
            "    jobQueueStatus: " + jobQueueStatus + "\n" +
            "}";
    }
}
