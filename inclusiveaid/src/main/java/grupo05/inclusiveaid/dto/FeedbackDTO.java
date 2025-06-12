package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para feedback dos usuários.
 * Utilizado para transferir dados de feedback sobre recursos de acessibilidade do sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class FeedbackDTO {
  public interface Create {}
  public interface Update {}

  /**
   * Identificador único do feedback.
   */
  private Long id;

  /**
   * Identificador do usuário que enviou o feedback.
   */
  @NotNull(groups = {Create.class, Update.class})
  private Long userId;

  /**
   * Mensagem de feedback do usuário.
   * Não pode estar em branco.
   */
  @NotBlank(groups = {Create.class, Update.class})
  private String message;

  private Instant timestamp;
}