package com.pragma.ordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class OrdenesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdenesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(2)
                .recordExceptions(
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.client.ResourceAccessException.class,
                    java.util.concurrent.TimeoutException.class,
                    java.io.IOException.class
                )
                .build();
    }

    @Bean
    public TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}