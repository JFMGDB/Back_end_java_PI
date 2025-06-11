package grupo05.inclusiveaid.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoiceProcessingConfigDTO {
    @NotBlank
    private String speechRecognitionModel;

    @Min(0)
    @Max(1)
    private double recognitionThreshold;

    private boolean enableNoiseReduction;
} 