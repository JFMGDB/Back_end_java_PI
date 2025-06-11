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
  public interface Update {}
  
  private Long id;

  @NotNull(groups = Create.class)
  private Long sessionId;

  @NotBlank(groups = {Create.class, Update.class})
  private String command;

  private String result;
  private Instant timestamp;
}