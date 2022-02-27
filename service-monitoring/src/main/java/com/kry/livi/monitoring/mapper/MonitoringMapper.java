package com.kry.livi.monitoring.mapper;

import com.kry.livi.monitoring.model.api.MonitoringRequestApiDTO;
import com.kry.livi.monitoring.model.api.MonitoringResponseApiDTO;
import com.kry.livi.monitoring.model.entity.Monitoring;

import java.time.ZoneId;

public final class MonitoringMapper {

    private MonitoringMapper() {
    }

    public static Monitoring toEntity(MonitoringRequestApiDTO apiDTO) {
        return new Monitoring()
                .setName(apiDTO.getName())
                .setUrl(apiDTO.getUrl());
    }

    public static MonitoringResponseApiDTO toApi(Monitoring entity) {
        return new MonitoringResponseApiDTO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setUrl(entity.getUrl())
                .setStatus(entity.getStatus())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }
}
