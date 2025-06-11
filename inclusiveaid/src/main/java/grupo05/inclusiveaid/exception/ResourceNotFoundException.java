package grupo05.inclusiveaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso não é encontrado no sistema.
 * Retorna status HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constrói uma exceção com uma mensagem personalizada.
     * @param message Mensagem detalhando o recurso não encontrado
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}