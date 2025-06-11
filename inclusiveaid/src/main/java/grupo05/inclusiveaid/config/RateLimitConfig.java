package grupo05.inclusiveaid.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.simple(100, Duration.ofMinutes(1));
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
} 