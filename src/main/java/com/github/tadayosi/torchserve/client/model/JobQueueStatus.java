package com.github.tadayosi.torchserve.client.model;

import com.github.tadayosi.torchserve.client.management.model.ModelsmodelNameJobQueueStatus;

public class JobQueueStatus {

    private Integer remainingCapacity = null;
    private Integer pendingRequests = null;

    public JobQueueStatus() {
    }

    public static JobQueueStatus from(ModelsmodelNameJobQueueStatus src) {
        if (src == null) {
            return null;
        }

        JobQueueStatus status = new JobQueueStatus();
        status.setRemainingCapacity(src.getRemainingCapacity());
        status.setPendingRequests(src.getPendingRequests());
        return status;
    }

    public Integer getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public Integer getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(Integer pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
            " remainingCapacity: " + remainingCapacity + "," +
            " pendingRequests: " + pendingRequests + " " +
            "}";
    }
}
