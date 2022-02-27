package com.kry.livi.monitoring.resource;

import com.kry.livi.monitoring.context.ResourceTestContext;
import com.kry.livi.monitoring.dao.MonitoringTestDAO;
import com.kry.livi.monitoring.error.ApiError;
import com.kry.livi.monitoring.model.api.MonitoringRequestApiDTO;
import com.kry.livi.monitoring.model.api.MonitoringResponseApiDTO;
import com.kry.livi.monitoring.model.entity.Monitoring;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static com.kry.livi.monitoring.documentation.MonitoringDocumentation.*;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.FAIL;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.OK;
import static com.kry.livi.monitoring.resource.ResourceConstants.*;
import static com.kry.livi.monitoring.util.error.ErrorTestUtils.expectError;
import static java.lang.Short.MAX_VALUE;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ResourceTestContext
public class MonitoringResourceTest {

    @Autowired
    private WebTestClient webTestClient;

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
        webTestClient.get()
                .uri(BASE_PATH + VERSION_1 + MONITORINGS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBodyList(MonitoringResponseApiDTO.class)
                .consumeWith(r -> {
                    List<MonitoringResponseApiDTO> monitorings = r.getResponseBody();
                    assertThat(monitorings).isNotNull();
                    assertThat(monitorings).hasSize(monitorings.size());
                    assertThat(monitorings)
                            .extracting(
                                    MonitoringResponseApiDTO::getId,
                                    MonitoringResponseApiDTO::getName,
                                    MonitoringResponseApiDTO::getUrl,
                                    MonitoringResponseApiDTO::getStatus,
                                    MonitoringResponseApiDTO::getCreatedAt,
                                    MonitoringResponseApiDTO::getUpdatedAt
                            )
                            .doesNotContainNull();

                })
                .consumeWith(findAllDocumentation());
    }

    @Test
    public void findById_shouldSucceed() {
        Monitoring searched = monitoringTestDAO.first();
        webTestClient.get()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, searched.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(MonitoringResponseApiDTO.class)
                .consumeWith(m -> {
                    MonitoringResponseApiDTO monitoring = m.getResponseBody();
                    assertThat(monitoring).isNotNull();
                    assertThat(monitoring.getId()).isEqualTo(searched.getId());
                    assertThat(monitoring.getName()).isEqualTo(searched.getName());
                    assertThat(monitoring.getUrl()).isEqualTo(searched.getUrl());
                    assertThat(monitoring.getCreatedAt()).isEqualTo(searched.getCreatedAt());
                    assertThat(monitoring.getUpdatedAt()).isEqualTo(searched.getUpdatedAt());
                    assertThat(monitoring.getStatus()).isIn(FAIL, OK);
                })
                .consumeWith(findByIdDocumentation());
    }

    @Test
    public void findById_whenBadId_shouldReturnNotFound() {
        webTestClient.get()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, MAX_VALUE)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(ApiError.class)
                .consumeWith(m -> expectError(m.getResponseBody()))
                .consumeWith(findByIdErrorDocumentation());
    }

    @Test
    public void create_shouldSucceed() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("service-created")
                .setUrl("https://www.google.fr");
        LocalDateTime before = now();
        webTestClient.post()
                .uri(BASE_PATH + VERSION_1 + MONITORINGS)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(MonitoringResponseApiDTO.class)
                .consumeWith(m -> {
                    MonitoringResponseApiDTO monitoring = m.getResponseBody();
                    assertThat(monitoring).isNotNull();
                    assertThat(monitoring.getId()).isNotNull();
                    assertThat(monitoring.getName()).isEqualTo(request.getName());
                    assertThat(monitoring.getUrl()).isEqualTo(request.getUrl());
                    assertThat(monitoring.getCreatedAt()).isAfterOrEqualTo(before);
                    assertThat(monitoring.getCreatedAt()).isBeforeOrEqualTo(now());
                    assertThat(monitoring.getUpdatedAt()).isAfterOrEqualTo(before);
                    assertThat(monitoring.getUpdatedAt()).isBeforeOrEqualTo(now());
                    assertThat(monitoring.getStatus()).isIn(FAIL, OK);
                })
                .consumeWith(createDocumentation());
    }

    @Test
    public void create_whenBadRequest_shouldReturnBadRequest() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("")
                .setUrl("badurl");
        webTestClient.post()
                .uri(BASE_PATH + VERSION_1 + MONITORINGS)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(ApiError.class)
                .consumeWith(m -> expectError(m.getResponseBody()))
                .consumeWith(createErrorDocumentation());
    }

    @Test
    public void update_shouldSucceed() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("service-created")
                .setUrl("https://www.google.fr");
        int id = monitoringTestDAO.first().getId();
        LocalDateTime before = now();
        webTestClient.put()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, id)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(MonitoringResponseApiDTO.class)
                .consumeWith(m -> {
                    MonitoringResponseApiDTO monitoring = m.getResponseBody();
                    assertThat(monitoring).isNotNull();
                    assertThat(monitoring.getId()).isNotNull();
                    assertThat(monitoring.getName()).isEqualTo(request.getName());
                    assertThat(monitoring.getUrl()).isEqualTo(request.getUrl());
                    assertThat(monitoring.getCreatedAt()).isNotNull();
                    assertThat(monitoring.getUpdatedAt()).isAfterOrEqualTo(before);
                    assertThat(monitoring.getUpdatedAt()).isBeforeOrEqualTo(now());
                    assertThat(monitoring.getStatus()).isIn(FAIL, OK);
                })
                .consumeWith(updateDocumentation());
    }

    @Test
    public void update_whenBadId_shouldReturnNotFound() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("service-created")
                .setUrl("https://www.google.fr");
        webTestClient.put()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, MAX_VALUE)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(ApiError.class)
                .consumeWith(m -> expectError(m.getResponseBody()))
                .consumeWith(findByIdErrorDocumentation());
    }

    @Test
    public void update_whenBadRequest_shouldReturnBadRequest() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("")
                .setUrl("badUrl");
        int id = monitoringTestDAO.first().getId();
        webTestClient.put()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, id)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody(ApiError.class)
                .consumeWith(m -> expectError(m.getResponseBody()))
                .consumeWith(updateErrorDocumentation());
    }

    @Test
    public void delete_shouldReturnNoContent() {
        int id = monitoringTestDAO.first().getId();
        webTestClient.delete()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, id)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .consumeWith(deleteDocumentation());
    }

    @Test
    public void delete_whenBadId_shouldReturnNoContent() {
        webTestClient.delete()
                .uri(BASE_PATH + VERSION_1 + MONITORING_PATH, MAX_VALUE)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .consumeWith(deleteDocumentation());
    }
}
