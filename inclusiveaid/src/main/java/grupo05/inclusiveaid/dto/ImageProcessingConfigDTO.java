package grupo05.inclusiveaid.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * DTO para configuração de processamento de imagem.
 * Utilizado para transferir parâmetros de configuração de processamento de imagem para agentes de IA.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageProcessingConfigDTO {
    /**
     * Resolução da imagem.
     */
    @NotBlank
    private String objectDetectionModel;

    @Min(0)
    @Max(1)
    private double detectionThreshold;

    private boolean enableOCR;
} 