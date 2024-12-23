package io.github.tadayosi.torchserve.client.model;

import io.github.tadayosi.torchserve.client.management.model.DescribeModel200ResponseInnerJobQueueStatus;

public class JobQueueStatus {

    private Integer remainingCapacity = null;
    private Integer pendingRequests = null;

    public JobQueueStatus() {
    }

    public static JobQueueStatus from(DescribeModel200ResponseInnerJobQueueStatus src) {
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
