package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para adaptações de acessibilidade.
 * Utilizado para transferir dados sobre adaptações realizadas para melhorar a acessibilidade do sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdaptationDTO {
  public interface Create {}
  public interface Update {}

  /**
   * Identificador único da adaptação.
   */
  private Long id;

  @NotBlank(groups = {Create.class,Update.class})
  private String name;

  /**
   * Descrição da adaptação realizada.
   */
  @NotBlank(groups = {Create.class,Update.class})
  private String description;

  @NotNull(groups = {Create.class,Update.class})
  private Long disabilityTypeId;

  /**
   * Data e hora em que a adaptação foi realizada.
   */
  private String timestamp;
}