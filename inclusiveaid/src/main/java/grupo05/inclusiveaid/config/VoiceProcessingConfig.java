package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VoiceProcessingConfig {
    private String speechRecognitionModel;
    private double recognitionThreshold;
    private boolean enableNoiseReduction;
} 