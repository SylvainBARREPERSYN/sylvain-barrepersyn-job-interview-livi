package com.kry.livi.monitoring.util.error;

import com.kry.livi.monitoring.error.ApiError;
import com.kry.livi.monitoring.error.MonitoringException;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class ErrorTestUtils {

    private ErrorTestUtils() {
    }

    public static void expectError(Throwable error, HttpStatus status) {
        assertThat(error).isNotNull();
        assertThat(error).isInstanceOf(MonitoringException.class);
        MonitoringException exception = (MonitoringException) error;
        assertThat(exception.getStatus()).isNotNull();
        assertThat(exception.getStatus()).isEqualTo(status);
        assertThat(exception.getMessage()).isNotNull();
        assertThat(exception.getMessage()).isNotBlank();
    }

    public static void expectError(ApiError apiError) {
        assertThat(apiError).isNotNull();
        assertThat(apiError.message()).isNotNull();
        assertThat(apiError.message()).isNotBlank();
    }
}
