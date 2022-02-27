package com.kry.livi.monitoring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableR2dbcAuditing
public class App {

    public static void main(String[] args) {
        run(App.class, args);
    }
}
