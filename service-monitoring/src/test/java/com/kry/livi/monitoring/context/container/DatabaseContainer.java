package com.kry.livi.monitoring.context.container;

import org.testcontainers.containers.MySQLContainer;

public class DatabaseContainer extends MySQLContainer<DatabaseContainer> {

    private static final String DB_NAME = "db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public DatabaseContainer() {
        super("mysql:8");
        withDatabaseName(DB_NAME);
        withUsername(DB_USERNAME);
        withPassword(DB_PASSWORD);
        withExposedPorts(3306);
        withInitScript("schema.sql");
    }

    public String url() {
        return "r2dbc:mysql://" + getHost() + ":" + getFirstMappedPort() + "/" + getDatabaseName();
    }

    public String username() {
        return getUsername();
    }

    public String password() {
        return getPassword();
    }
}
