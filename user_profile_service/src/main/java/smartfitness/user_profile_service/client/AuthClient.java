package smartfitness.user_profile_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/api/auth/validate")
    @CircuitBreaker(name = "authClientCB", fallbackMethod = "fallbackValidateToken")
    @Retry(name = "authClientRetry")
    boolean validateToken(@RequestHeader("Authorization") String token);

    default boolean fallbackValidateToken(String token, Throwable t) {
        System.out.println("Fallback triggered for token validation: " + t.getMessage());
        return false;
    }
}