package com.github.tadayosi.torchserve.client.model;

import java.util.List;
import java.util.stream.Collectors;

import com.github.tadayosi.torchserve.client.management.model.DescribeModel200ResponseInnerWorkersInner;

public class Worker {

    private String id = null;
    private String startTime = null;
    private Boolean gpu = null;
    private Status status = null;

    public Worker() {
    }

    public static Worker from(DescribeModel200ResponseInnerWorkersInner src) {
        if (src == null) {
            return null;
        }

        Worker worker = new Worker();
        worker.setId(src.getId());
        worker.setStartTime(src.getStartTime());
        worker.setGpu(src.getGpu());
        worker.setStatus(Status.from(src.getStatus()));
        return worker;
    }

    public static List<Worker> from(List<DescribeModel200ResponseInnerWorkersInner> src) {
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
            " id: " + id + "," +
            " startTime: " + startTime + "," +
            " gpu: " + gpu + "," +
            " status: " + status + " " +
            "}";
    }

    public enum Status {
        READY,
        LOADING,
        UNLOADING;

        public static Status from(DescribeModel200ResponseInnerWorkersInner.StatusEnum status) {
            return switch (status) {
                case READY -> READY;
                case LOADING -> LOADING;
                case UNLOADING -> UNLOADING;
            };
        }
    }
}
