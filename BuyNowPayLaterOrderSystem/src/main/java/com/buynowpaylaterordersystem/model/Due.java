package com.buynowpaylaterordersystem.model;

import java.time.LocalDateTime;

public class Due {
    String orderId;
    Double dueAmount;
    DUE_STATUS dueStatus;
    LocalDateTime dueCreatedTs;
    LocalDateTime dueDate;

    public Due(String orderId, Double dueAmount, DUE_STATUS dueStatus, LocalDateTime dueCreatedTs, LocalDateTime dueDate) {
        this.orderId = orderId;
        this.dueAmount = dueAmount;
        this.dueStatus = dueStatus;
        this.dueCreatedTs = dueCreatedTs;
        this.dueDate = dueDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public DUE_STATUS getDueStatus() {
        return dueStatus;
    }

    public void setDueStatus(DUE_STATUS dueStatus) {
        this.dueStatus = dueStatus;
    }

    public LocalDateTime getDueCreatedTs() {
        return dueCreatedTs;
    }

    public void setDueCreatedTs(LocalDateTime dueCreatedTs) {
        this.dueCreatedTs = dueCreatedTs;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
