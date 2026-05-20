package com.example.Ejercicio10.loghub.dto;

public class LogHubLogRequest {

    private String message;
    private String logLevel;
    private Long appId;

    public LogHubLogRequest(String message, String logLevel, Long appId) {
        this.message = message;
        this.logLevel = logLevel;
        this.appId = appId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
