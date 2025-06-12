package grupo05.inclusiveaid.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para requisição de autenticação.
 * Utilizado para transferir as credenciais de autenticação do usuário.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
public class AuthRequest {
  /**
   * Email do usuário.
   */
  @Email @NotBlank
  private String email;
  /**
   * Senha do usuário.
   */
  @NotBlank
  private String password;
}