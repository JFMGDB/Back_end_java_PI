package grupo05.inclusiveaid.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Payload de login.
 */
@Data
public class AuthRequest {
  @Email @NotBlank
  private String email;
  @NotBlank
  private String password;
}