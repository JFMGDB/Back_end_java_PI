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
public class NLPConfigDTO {
    @NotBlank
    private String languageModel;

    @Min(0)
    @Max(1)
    private double confidenceThreshold;

    private boolean enableContextAwareness;
} 