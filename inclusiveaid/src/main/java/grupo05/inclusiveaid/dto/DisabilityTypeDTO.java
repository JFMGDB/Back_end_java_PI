package grupo05.inclusiveaid.dto;
import lombok.*;
import jakarta.validation.constraints.*;
/**
 * DTO de DisabilityType.
 */
@Data @Builder
public class DisabilityTypeDTO {
  public interface Create {}
  public interface Update {}

  private Long id;

  @NotBlank(groups = {Create.class, Update.class})
  private String name;

  @NotBlank(groups = {Create.class, Update.class})
  private String description;
}