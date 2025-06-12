package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para sessões de usuário.
 * Utilizado para transferir dados relacionados às sessões de uso do sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
public class SessionDTO {
  public interface Create {}

  /**
   * Identificador único da sessão.
   */
  private Long id;

  /**
   * Identificador do usuário associado à sessão.
   */
  @NotNull(groups = Create.class)
  private Long userId;

  /**
   * Data e hora de início da sessão.
   */
  private Instant startedAt;

  /**
   * Data e hora de término da sessão.
   */
  private Instant endedAt;
}