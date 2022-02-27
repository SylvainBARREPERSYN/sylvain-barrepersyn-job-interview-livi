package com.kry.livi.monitoring.resource;

public final class ResourceConstants {

    public static final String BASE_PATH = "/service-monitoring";

    public static final String VERSION_1 = "/v1";

    public static final String MONITORINGS = "/monitorings";
    public static final String MONITORING_ID = "monitoringId";
    public static final String MONITORING_PATH = MONITORINGS + "/{" + MONITORING_ID + "}";
    public static final String MONITORING_CHECK = MONITORING_PATH + "/check";

    private ResourceConstants() {
    }
}
