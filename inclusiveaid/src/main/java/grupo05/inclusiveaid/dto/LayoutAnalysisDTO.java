package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para análise de layout.
 * Utilizado para transferir dados sobre análises de layout realizadas pelo sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class LayoutAnalysisDTO {
    /**
     * Interface para validação na criação de uma nova análise.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de uma análise existente.
     */
    public interface Update {}
    
    /**
     * Identificador único da análise.
     */
    private Long id;

    /**
     * Dados da análise em formato JSON.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String analysisData;

    /**
     * Data e hora da análise.
     */
    private String timestamp;

    /**
     * Identificador do usuário que solicitou a análise.
     */
    @NotNull(groups = {Create.class, Update.class})
    private Long userId;
}