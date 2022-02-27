package com.kry.livi.monitoring.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

abstract class TestDAO<T, ID> {

    private final R2dbcRepository<T, ID> repository;

    TestDAO(R2dbcRepository<T, ID> repository) {
        this.repository = repository;
    }

    public void init() {
        repository
                .saveAll(buildEntities())
                .blockLast();
    }

    public void clean() {
        repository
                .deleteAll()
                .block();
    }

    public int count() {
        return requireNonNull(repository.count()
                .block())
                .intValue();
    }

    public T first() {
        return repository
                .findAll()
                .blockFirst();
    }

    abstract List<T> buildEntities();
}
