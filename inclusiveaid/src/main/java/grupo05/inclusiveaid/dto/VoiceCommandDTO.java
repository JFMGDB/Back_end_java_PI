package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para VoiceCommand.
 */
@Data @Builder
public class VoiceCommandDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long sessionId;

  @NotBlank(groups = Create.class)
  private String command;

  private String result;
  private Instant timestamp;
}