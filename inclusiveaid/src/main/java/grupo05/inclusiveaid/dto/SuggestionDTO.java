package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para sugestões de acessibilidade.
 * Utilizado para transferir dados relacionados às sugestões geradas pelo sistema
 * para melhorar a acessibilidade da interface.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
public class SuggestionDTO {
    /**
     * Interface para validação na criação de uma nova sugestão.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de uma sugestão existente.
     */
    public interface Update {}
    
    /**
     * Identificador único da sugestão.
     */
    private Long id;

    /**
     * Identificador da análise de layout associada à sugestão.
     * Não pode ser nulo na criação.
     */
    @NotNull(groups = Create.class)
    private Long layoutAnalysisId;

    /**
     * Mensagem contendo a sugestão de melhoria.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String message;
}