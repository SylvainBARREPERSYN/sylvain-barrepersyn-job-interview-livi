package com.kry.livi.monitoring.context;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Inherited
@Target(TYPE)
@Retention(RUNTIME)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AppTestContext
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
public @interface ResourceTestContext {
}
