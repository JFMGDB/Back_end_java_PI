package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para Subtitle.
 */
@Data @Builder
public class SubtitleDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long sessionId;

  @NotBlank(groups = Create.class)
  private String text;

  private Instant timestamp;
}