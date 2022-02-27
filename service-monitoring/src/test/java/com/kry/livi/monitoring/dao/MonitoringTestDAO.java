package com.kry.livi.monitoring.dao;

import com.kry.livi.monitoring.model.entity.Monitoring;
import com.kry.livi.monitoring.repository.MonitoringRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.kry.livi.monitoring.model.enums.MonitoringStatus.FAIL;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.OK;
import static java.util.List.of;

@Component
public class MonitoringTestDAO extends TestDAO<Monitoring, Integer> {

    public MonitoringTestDAO(MonitoringRepository repository) {
        super(repository);
    }

    @Override
    List<Monitoring> buildEntities() {
        return of(
                new Monitoring()
                        .setName("service-1")
                        .setStatus(OK)
                        .setUrl("http://service1.com"),
                new Monitoring()
                        .setName("service-2")
                        .setStatus(FAIL)
                        .setUrl("http://service2.com")
        );
    }
}
