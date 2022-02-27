package com.kry.livi.monitoring.resource;

import com.kry.livi.monitoring.mapper.MonitoringMapper;
import com.kry.livi.monitoring.model.api.MonitoringRequestApiDTO;
import com.kry.livi.monitoring.model.api.MonitoringResponseApiDTO;
import com.kry.livi.monitoring.service.MonitoringService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.kry.livi.monitoring.resource.ResourceConstants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static reactor.core.publisher.Mono.just;

@RestController
public record MonitoringResource(MonitoringService monitoringService) {

    @GetMapping(BASE_PATH + VERSION_1 + MONITORINGS)
    public Flux<MonitoringResponseApiDTO> findAll() {
        return monitoringService
                .findAll()
                .map(MonitoringMapper::toApi);
    }

    @PostMapping(BASE_PATH + VERSION_1 + MONITORINGS)
    @ResponseStatus(CREATED)
    public Mono<MonitoringResponseApiDTO> create(@Valid @RequestBody MonitoringRequestApiDTO monitoring) {
        return just(monitoring)
                .map(MonitoringMapper::toEntity)
                .flatMap(monitoringService::create)
                .map(MonitoringMapper::toApi);
    }

    @GetMapping(BASE_PATH + VERSION_1 + MONITORING_PATH)
    public Mono<MonitoringResponseApiDTO> findById(@PathVariable(MONITORING_ID) int monitoringId) {
        return monitoringService
                .findById(monitoringId)
                .map(MonitoringMapper::toApi);
    }

    @PutMapping(BASE_PATH + VERSION_1 + MONITORING_PATH)
    public Mono<MonitoringResponseApiDTO> update(@PathVariable(MONITORING_ID) int monitoringId,
                                                 @Valid @RequestBody MonitoringRequestApiDTO monitoring) {
        return just(monitoring)
                .map(MonitoringMapper::toEntity)
                .flatMap(m -> monitoringService.update(monitoringId, m))
                .map(MonitoringMapper::toApi);
    }

    @DeleteMapping(BASE_PATH + VERSION_1 + MONITORING_PATH)
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable(MONITORING_ID) int monitoringId) {
        return monitoringService
                .delete(monitoringId);
    }
}
