package com.kry.livi.monitoring.service;

import com.kry.livi.monitoring.context.ServiceTestContext;
import com.kry.livi.monitoring.dao.MonitoringTestDAO;
import com.kry.livi.monitoring.model.entity.Monitoring;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.kry.livi.monitoring.model.enums.MonitoringStatus.FAIL;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.OK;
import static com.kry.livi.monitoring.util.error.ErrorTestUtils.expectError;
import static java.lang.Short.MAX_VALUE;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static reactor.test.StepVerifier.create;

@ServiceTestContext
public class MonitoringServiceTest {

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private MonitoringTestDAO monitoringTestDAO;

    @BeforeEach
    public void setUp() {
        monitoringTestDAO.init();
    }

    @AfterEach
    public void tearDown() {
        monitoringTestDAO.clean();
    }

    @Test
    public void findAll_shouldSucceed() {
        create(monitoringService.findAll())
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void findById_shouldReturnFoundedMonitoring() {
        Monitoring searched = monitoringTestDAO.first();
        create(monitoringService.findById(searched.getId()))
                .expectSubscription()
                .consumeNextWith(m -> {
                    assertThat(m).isNotNull();
                    assertThat(m.getId()).isNotNull();
                    assertThat(m.getName()).isNotBlank();
                    assertThat(m.getUrl()).isNotBlank();
                    assertThat(m.getCreatedAt()).isNotNull();
                    assertThat(m.getUpdatedAt()).isNotNull();
                })
                .verifyComplete();
    }

    @Test
    public void findById_whenBadId_shouldFail() {
        create(monitoringService.findById(MAX_VALUE))
                .expectSubscription()
                .consumeErrorWith(e -> expectError(e, NOT_FOUND))
                .verify();
    }

    @Test
    public void create_shouldSucceed() {
        Monitoring toCreate = new Monitoring()
                .setName("service-created")
                .setUrl("https://www.google.fr");
        LocalDateTime before = now();
        create(monitoringService.create(toCreate))
                .expectSubscription()
                .consumeNextWith(m -> {
                    assertThat(m).isNotNull();
                    assertThat(m.getId()).isNotNull();
                    assertThat(m.getName()).isEqualTo(toCreate.getName());
                    assertThat(m.getUrl()).isEqualTo(toCreate.getUrl());
                    assertThat(m.getCreatedAt()).isAfterOrEqualTo(before);
                    assertThat(m.getCreatedAt()).isBeforeOrEqualTo(now());
                    assertThat(m.getUpdatedAt()).isAfterOrEqualTo(before);
                    assertThat(m.getUpdatedAt()).isBeforeOrEqualTo(now());
                    assertThat(m.getStatus()).isIn(FAIL, OK);
                })
                .verifyComplete();
    }

    @Test
    public void update_shouldSucceed() {
        Monitoring toUpdate = new Monitoring()
                .setName("service-updated")
                .setUrl("https://www.google2.fr");
        int id = monitoringTestDAO.first().getId();
        LocalDateTime before = now();
        create(monitoringService.update(id, toUpdate))
                .expectSubscription()
                .consumeNextWith(m -> {
                    assertThat(m).isNotNull();
                    assertThat(m.getId()).isNotNull();
                    assertThat(m.getName()).isEqualTo(toUpdate.getName());
                    assertThat(m.getUrl()).isEqualTo(toUpdate.getUrl());
                    assertThat(m.getCreatedAt()).isNotNull();
                    assertThat(m.getUpdatedAt()).isAfterOrEqualTo(before);
                    assertThat(m.getUpdatedAt()).isBeforeOrEqualTo(now());
                    assertThat(m.getStatus()).isIn(FAIL, OK);
                })
                .verifyComplete();
    }

    @Test
    public void update_whenBadId_shouldFail() {
        Monitoring toUpdate = new Monitoring()
                .setName("service-updated")
                .setUrl("https://www.google2.fr");
        create(monitoringService.update(MAX_VALUE, toUpdate))
                .expectSubscription()
                .consumeErrorWith(e -> expectError(e, NOT_FOUND))
                .verify();
    }

    @Test
    public void delete_shouldSucceed() {
        int id = monitoringTestDAO.first().getId();
        int dbSize = monitoringTestDAO.count();
        create(monitoringService.delete(id))
                .expectSubscription()
                .verifyComplete();
    }
}
