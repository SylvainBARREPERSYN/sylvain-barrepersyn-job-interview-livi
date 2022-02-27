package com.kry.livi.monitoring.error;

import org.springframework.http.HttpStatus;

public class MonitoringException extends Exception {

    private final String reason;
    private final HttpStatus status;

    public MonitoringException(String reason, HttpStatus status) {
        super(reason);
        this.reason = reason;
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
