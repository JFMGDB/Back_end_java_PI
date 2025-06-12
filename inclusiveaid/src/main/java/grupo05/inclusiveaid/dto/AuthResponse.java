package grupo05.inclusiveaid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Retorno do login: token JWT.
 */
@Data @AllArgsConstructor
public class AuthResponse {
    /**
     * Token JWT de autenticação.
     */
    private String token;
}