package com.kry.livi.monitoring.model.entity;

import com.kry.livi.monitoring.model.enums.MonitoringStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class Monitoring {

    @Id
    private Integer id;

    private String name;

    private String url;

    private MonitoringStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public Monitoring setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Monitoring setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Monitoring setUrl(String url) {
        this.url = url;
        return this;
    }

    public MonitoringStatus getStatus() {
        return status;
    }

    public Monitoring setStatus(MonitoringStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Monitoring setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Monitoring setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
