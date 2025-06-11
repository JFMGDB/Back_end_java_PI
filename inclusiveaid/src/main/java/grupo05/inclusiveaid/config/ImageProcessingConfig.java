package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ImageProcessingConfig {
    private String objectDetectionModel;
    private double detectionThreshold;
    private boolean enableOCR;
} 