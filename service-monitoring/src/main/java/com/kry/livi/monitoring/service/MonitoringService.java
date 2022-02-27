package com.kry.livi.monitoring.service;

import com.kry.livi.monitoring.error.NotFoundException;
import com.kry.livi.monitoring.model.entity.Monitoring;
import com.kry.livi.monitoring.model.enums.MonitoringStatus;
import com.kry.livi.monitoring.repository.MonitoringRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.kry.livi.monitoring.model.enums.MonitoringStatus.FAIL;
import static com.kry.livi.monitoring.model.enums.MonitoringStatus.OK;
import static java.util.Objects.requireNonNullElseGet;
import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

@Service
@EnableScheduling
public record MonitoringService(MonitoringRepository monitoringRepository) {

    private static Monitoring update(Monitoring source, Monitoring target) {
        return requireNonNullElseGet(target, Monitoring::new)
                .setName(source.getName())
                .setUrl(source.getUrl());
    }

    public Flux<Monitoring> findAll() {
        return monitoringRepository
                .findAll();
    }

    public Mono<Monitoring> findById(int id) {
        return monitoringRepository
                .findById(id)
                .switchIfEmpty(error(new NotFoundException(id)));
    }

    public Mono<Monitoring> create(Monitoring monitoring) {
        return just(monitoring)
                .flatMap(this::monitor)
                .flatMap(monitoringRepository::save);
    }

    public Mono<Monitoring> update(int id, Monitoring monitoring) {
        return findById(id)
                .map(m -> update(monitoring, m))
                .flatMap(this::monitor)
                .flatMap(monitoringRepository::save);
    }

    public Mono<Void> delete(int id) {
        return monitoringRepository
                .deleteById(id);
    }

    public Flux<Monitoring> updateServices() {
        return findAll()
                .flatMap(s -> update(s.getId(), s));
    }

    private Mono<Monitoring> monitor(Monitoring monitoring) {
        return just(monitoring)
                .map(Monitoring::getUrl)
                .flatMap(this::retrieveStatus)
                .map(monitoring::setStatus);
    }

    private Mono<MonitoringStatus> retrieveStatus(String url) {
        return just(url)
                .map(WebClient::create)
                .flatMap(w -> w.get()
                               .retrieve()
                               .toBodilessEntity())
                .map(ResponseEntity::getStatusCode)
                .map(s -> s == HttpStatus.OK ? OK : FAIL)
                .onErrorReturn(FAIL);
    }

    @Profile("!test")
    @Scheduled(fixedDelay = 60_000) // all minutes
    public void checkServices() {
        updateServices().blockLast();
    }
}
