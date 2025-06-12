package grupo05.inclusiveaid.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * DTO para configuração de processamento de voz.
 * Utilizado para transferir parâmetros de configuração de processamento de voz para agentes de IA.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoiceProcessingConfigDTO {
    /**
     * Idioma do reconhecimento de voz.
     */
    @NotBlank
    private String speechRecognitionModel;

    /**
     * Sensibilidade do reconhecimento de voz.
     */
    @Min(0)
    @Max(1)
    private double recognitionThreshold;

    private boolean enableNoiseReduction;
} 