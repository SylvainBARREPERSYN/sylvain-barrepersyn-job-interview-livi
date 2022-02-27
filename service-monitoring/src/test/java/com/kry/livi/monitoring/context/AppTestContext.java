package com.kry.livi.monitoring.context;

import com.kry.livi.monitoring.context.extension.DatasourceExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Target(TYPE)
@Retention(RUNTIME)
@ActiveProfiles("test")
@ExtendWith({DatasourceExtension.class, SpringExtension.class})
@DirtiesContext
public @interface AppTestContext {
}
