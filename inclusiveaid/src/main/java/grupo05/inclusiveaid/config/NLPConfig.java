package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NLPConfig {
    private String languageModel;
    private double confidenceThreshold;
    private boolean enableContextAwareness;
} 