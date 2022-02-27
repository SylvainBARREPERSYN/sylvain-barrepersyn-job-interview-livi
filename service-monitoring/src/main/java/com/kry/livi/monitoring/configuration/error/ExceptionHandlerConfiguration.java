package com.kry.livi.monitoring.configuration.error;

import com.kry.livi.monitoring.error.ApiError;
import com.kry.livi.monitoring.error.MonitoringException;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class ExceptionHandlerConfiguration {

    Logger logger = getLogger(ExceptionHandlerConfiguration.class);

    @ExceptionHandler(MonitoringException.class)
    public ResponseEntity<ApiError> handle(MonitoringException exception) {
        return status(exception.getStatus())
                .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiError> handle(WebExchangeBindException exception) {
        logger.error(exception.getMessage(), exception);
        return status(BAD_REQUEST)
                .body(new ApiError(retrieveFeedback(exception.getBindingResult().getAllErrors())));
    }

    private static String retrieveFeedback(List<ObjectError> allErrors) {
        return allErrors
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(fe -> fe.getField() + " : " + fe.getDefaultMessage())
                .collect(joining(", "));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handle(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return status(INTERNAL_SERVER_ERROR)
                .body(new ApiError(exception.getMessage()));
    }

}
