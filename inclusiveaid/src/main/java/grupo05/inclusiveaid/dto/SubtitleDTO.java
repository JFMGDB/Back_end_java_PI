package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para legendas de acessibilidade.
 * Utilizado para transferir dados relacionados às legendas geradas pelo sistema
 * para melhorar a acessibilidade para usuários com deficiência auditiva.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SubtitleDTO {
    /**
     * Interface para validação na criação de uma nova legenda.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de uma legenda existente.
     */
    public interface Update {}
    
    /**
     * Identificador único da legenda.
     */
    private Long id;

    /**
     * Identificador da sessão onde a legenda foi gerada.
     * Não pode ser nulo na criação.
     */
    @NotNull(groups = Create.class)
    private Long sessionId;

    /**
     * Texto da legenda.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String text;

    /**
     * Data e hora em que a legenda foi gerada.
     */
    private Instant timestamp;
}