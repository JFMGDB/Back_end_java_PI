package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para Session.
 */
@Data @Builder
public class SessionDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long userId;

  private Instant startedAt;
  private Instant endedAt;
}