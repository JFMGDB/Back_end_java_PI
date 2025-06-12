package grupo05.inclusiveaid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para resposta de login.
 * Utilizado para transferir o token de autenticação JWT após login bem-sucedido.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    /**
     * Token JWT de autenticação.
     */
    private String token;
} 