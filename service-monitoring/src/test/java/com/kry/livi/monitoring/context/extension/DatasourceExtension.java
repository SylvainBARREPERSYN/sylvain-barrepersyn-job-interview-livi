package com.kry.livi.monitoring.context.extension;

import com.kry.livi.monitoring.context.container.DatabaseContainer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.junit.jupiter.Container;

import static java.lang.System.setProperty;

public class DatasourceExtension implements BeforeAllCallback {

    @Container
    static DatabaseContainer container;

    @Override
    public void beforeAll(ExtensionContext context) {
        container = new DatabaseContainer();
        container.start();
        setProperty("spring.r2dbc.url", container.url());
        setProperty("spring.r2dbc.username", container.username());
        setProperty("spring.r2dbc.password", container.password());
    }
}
