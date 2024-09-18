package com.github.tadayosi.torchserve.client.model;

import java.util.List;
import java.util.stream.Collectors;

import com.github.tadayosi.torchserve.client.management.model.ModelsmodelNameWorkers;

public class Worker {

    private String id = null;
    private String startTime = null;
    private Boolean gpu = null;
    private Status status = null;

    public Worker() {
    }

    public static Worker from(ModelsmodelNameWorkers src) {
        Worker worker = new Worker();
        worker.setId(src.getId());
        worker.setStartTime(src.getStartTime());
        worker.setGpu(src.isGpu());
        worker.setStatus(Status.from(src.getStatus()));
        return worker;
    }

    public static List<Worker> from(List<ModelsmodelNameWorkers> src) {
        return src.stream().map(Worker::from).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Boolean getGpu() {
        return gpu;
    }

    public void setGpu(Boolean gpu) {
        this.gpu = gpu;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        READY,
        LOADING,
        UNLOADING;

        public static Status from(ModelsmodelNameWorkers.StatusEnum status) {
            return switch (status) {
                case READY -> READY;
                case LOADING -> LOADING;
                case UNLOADING -> UNLOADING;
            };
        }
    }
}
