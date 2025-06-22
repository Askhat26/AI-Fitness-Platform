package smartfitness.user_profile_service.config;



import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.retry.configuration.RetryConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerConfigCustomizer customCircuitBreaker() {
        return CircuitBreakerConfigCustomizer
                .of("authClientCB", builder -> builder
                        .failureRateThreshold(50)
                        .waitDurationInOpenState(Duration.ofSeconds(10))
                        .slidingWindowSize(10));
    }

    @Bean
    public RetryConfigCustomizer customRetry() {
        return RetryConfigCustomizer
                .of("authClientRetry", builder -> builder
                        .maxAttempts(3)
                        .waitDuration(Duration.ofSeconds(1)));
    }
}

