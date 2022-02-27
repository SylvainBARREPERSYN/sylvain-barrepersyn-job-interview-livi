package com.kry.livi.monitoring.model.api;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MonitoringRequestApiDTO {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    @URL
    private String url;

    public String getName() {
        return name;
    }

    public MonitoringRequestApiDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MonitoringRequestApiDTO setUrl(String url) {
        this.url = url;
        return this;
    }
}
