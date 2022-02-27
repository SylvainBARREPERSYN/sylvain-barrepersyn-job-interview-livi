package com.kry.livi.monitoring.model.api;

import com.kry.livi.monitoring.model.enums.MonitoringStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonitoringResponseApiDTO {

    private int id;

    private String name;

    private String url;

    private MonitoringStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public MonitoringResponseApiDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MonitoringResponseApiDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MonitoringResponseApiDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public MonitoringStatus getStatus() {
        return status;
    }

    public MonitoringResponseApiDTO setStatus(MonitoringStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MonitoringResponseApiDTO setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public MonitoringResponseApiDTO setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
