package grupo05.inclusiveaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma tentativa de cadastro é feita com um email já existente no sistema.
 * Esta exceção é mapeada para o status HTTP 409 (Conflict) e é utilizada para indicar
 * que o email fornecido já está em uso por outro usuário.
 * 
 * Esta exceção é importante para:
 * - Manter a unicidade dos emails no sistema
 * - Prevenir duplicação de contas
 * - Garantir a integridade dos dados dos usuários
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {
    
    /**
     * Constrói uma exceção com uma mensagem personalizada.
     * A mensagem deve informar ao usuário que o email já está em uso e,
     * se possível, sugerir alternativas.
     * 
     * @param message Mensagem informando que o email já está em uso
     */
    public DuplicateEmailException(String message) {
        super(message);
    }
} 