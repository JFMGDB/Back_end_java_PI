package grupo05.inclusiveaid.dto;

import lombok.*;

/**
 * DTO para requisição de login.
 * Utilizado para transferir as credenciais de acesso do usuário.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
public class LoginRequest {
    /**
     * Email do usuário.
     */
    private String email;
    /**
     * Senha do usuário.
     */
    private String password;
} 