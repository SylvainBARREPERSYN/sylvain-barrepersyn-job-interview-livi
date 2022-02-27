package com.kry.livi.monitoring.repository;

import com.kry.livi.monitoring.model.entity.Monitoring;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface MonitoringRepository extends R2dbcRepository<Monitoring, Integer> {
}
