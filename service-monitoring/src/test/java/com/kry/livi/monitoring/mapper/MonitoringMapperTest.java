package com.kry.livi.monitoring.mapper;

import com.kry.livi.monitoring.model.api.MonitoringRequestApiDTO;
import com.kry.livi.monitoring.model.api.MonitoringResponseApiDTO;
import com.kry.livi.monitoring.model.entity.Monitoring;
import org.junit.jupiter.api.Test;

import static com.kry.livi.monitoring.mapper.MonitoringMapper.toApi;
import static com.kry.livi.monitoring.mapper.MonitoringMapper.toEntity;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.OK;
import static java.time.LocalDate.now;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MonitoringMapperTest {

    @Test
    public void toEntity_shouldMap() {
        MonitoringRequestApiDTO request = new MonitoringRequestApiDTO()
                .setName("name")
                .setUrl("https://www.google.fr");
        Monitoring output = toEntity(request);
        assertThat(output).isNotNull();
        assertThat(output.getId()).isNull();
        assertThat(output.getName()).isEqualTo(request.getName());
        assertThat(output.getUrl()).isEqualTo(request.getUrl());
        assertThat(output.getUpdatedAt()).isNull();
        assertThat(output.getCreatedAt()).isNull();
        assertThat(output.getStatus()).isNull();
    }

    @Test
    public void toApi_shouldMap() {
        Monitoring entity = new Monitoring()
                .setId(1)
                .setName("name")
                .setUrl("https://www.google.fr")
                .setCreatedAt(java.time.LocalDateTime.now())
                .setUpdatedAt(java.time.LocalDateTime.now())
                .setStatus(OK);
        MonitoringResponseApiDTO output = toApi(entity);
        assertThat(output).isNotNull();
        assertThat(output.getId()).isEqualTo(entity.getId());
        assertThat(output.getName()).isEqualTo(entity.getName());
        assertThat(output.getUrl()).isEqualTo(entity.getUrl());
        assertThat(output.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
        assertThat(output.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(output.getStatus()).isEqualTo(entity.getStatus());
    }
}
