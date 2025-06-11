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
public class ImageProcessingConfigDTO {
    @NotBlank
    private String objectDetectionModel;

    @Min(0)
    @Max(1)
    private double detectionThreshold;

    private boolean enableOCR;
} 