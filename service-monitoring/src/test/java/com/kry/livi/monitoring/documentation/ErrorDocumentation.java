package com.kry.livi.monitoring.documentation;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.List;

import static java.util.List.of;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public final class ErrorDocumentation {

    private ErrorDocumentation() {
    }

    public static List<FieldDescriptor> errorResponseBodyDescription() {
        return of(
                fieldWithPath("message")
                        .type(STRING)
                        .description("Description of the problem")
        );
    }
}
