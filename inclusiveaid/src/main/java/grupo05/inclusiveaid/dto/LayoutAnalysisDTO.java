package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para análise de layout.
 * Utilizado para transferir dados relacionados à análise de layout de interfaces
 * para melhorar a acessibilidade do sistema.
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
     * Identificador único da análise de layout.
     */
    private Long id;

    /**
     * Identificador do usuário que realizou a análise.
     * Não pode ser nulo.
     */
    @NotNull(groups = {Create.class, Update.class})
    private Long userId;

    /**
     * Resultado da análise de layout.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String analysis;

    /**
     * Data e hora em que a análise foi realizada.
     */
    private Instant timestamp;
}