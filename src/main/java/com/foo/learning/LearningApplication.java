package com.foo.learning;

import io.leiva.app.config.RestTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestTemplateConfig.class)
public class LearningApplication {


    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }
}
