package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para sessões de usuário.
 * Utilizado para transferir dados sobre sessões ativas de usuários no sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SessionDTO {
    public interface Create {}
    public interface Update {}

    /**
     * Identificador único da sessão.
     */
    private Long id;

    /**
     * Identificador do usuário da sessão.
     */
    @NotNull(groups = {Create.class, Update.class})
    private Long userId;

    /**
     * Data e hora de início da sessão.
     */
    private String startTime;

    /**
     * Data e hora de fim da sessão.
     */
    private String endTime;

    /**
     * Status da sessão.
     */
    private String status;
}