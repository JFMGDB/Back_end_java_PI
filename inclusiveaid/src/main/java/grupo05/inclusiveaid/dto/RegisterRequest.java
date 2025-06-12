package grupo05.inclusiveaid.dto;

import lombok.Data;

/**
 * DTO para requisição de registro de novo usuário.
 * Utilizado para transferir os dados necessários para criar uma nova conta de usuário.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
public class RegisterRequest {
    /**
     * Nome do usuário.
     */
    private String name;
    /**
     * Email do usuário.
     */
    private String email;
    /**
     * Senha do usuário.
     */
    private String password;
} 