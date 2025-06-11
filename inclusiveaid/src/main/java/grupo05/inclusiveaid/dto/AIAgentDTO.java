package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIAgentDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String version;

    private boolean isActive;
    private LocalDateTime lastUpdate;
    
    // Configurations
    @NotNull
    private NLPConfigDTO nlpConfig;

    @NotNull
    private ImageProcessingConfigDTO imageConfig;

    @NotNull
    private VoiceProcessingConfigDTO voiceConfig;
    
    // Relationships
    private Set<Long> activeUserIds;
    private Set<Long> disabilityConfigIds;
} 