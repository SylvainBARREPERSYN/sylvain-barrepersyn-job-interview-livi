package com.kry.livi.monitoring.error;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends MonitoringException {

    public NotFoundException(Object resourceId) {
        super("Resource with id '" + resourceId.toString() + "' does not exist", NOT_FOUND);
    }
}
