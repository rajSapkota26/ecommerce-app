package com.IntermTermProject.com.dashboard.model;

public class TransactionDetails {
    private String status;
    private String referenceId;
    private String date;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "status='" + status + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
