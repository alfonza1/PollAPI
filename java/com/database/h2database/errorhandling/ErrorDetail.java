package com.database.h2database.errorhandling;

public class ErrorDetail {

    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", timeStamp=" + timeStamp +
                ", developerMessage='" + developerMessage + '\'' +
                '}';
    }
}
