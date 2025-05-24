package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
/**
 * DTO para Adaptation.
 */
@Data @Builder
public class AdaptationDTO {
  public interface Create {}
  public interface Update {}

  private Long id;

  @NotBlank(groups = {Create.class,Update.class})
  private String name;

  @NotBlank(groups = {Create.class,Update.class})
  private String description;

  @NotNull(groups = {Create.class,Update.class})
  private Long disabilityTypeId;
}