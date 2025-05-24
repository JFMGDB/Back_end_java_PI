package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para Feedback.
 */
@Data @Builder
public class FeedbackDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long userId;

  @NotBlank(groups = Create.class)
  private String message;

  private Instant timestamp;
}