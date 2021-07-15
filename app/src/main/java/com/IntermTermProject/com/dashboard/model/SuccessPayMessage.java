package com.IntermTermProject.com.dashboard.model;

public class SuccessPayMessage {
    private String technicalSuccessMessage;
    private String successMessage;

    public String getTechnicalSuccessMessage() {
        return technicalSuccessMessage;
    }

    public void setTechnicalSuccessMessage(String technicalSuccessMessage) {
        this.technicalSuccessMessage = technicalSuccessMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @Override
    public String toString() {
        return "SuccessPayMessage{" +
                "technicalSuccessMessage='" + technicalSuccessMessage + '\'' +
                ", successMessage='" + successMessage + '\'' +
                '}';
    }
}
