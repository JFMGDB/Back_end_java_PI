package grupo05.inclusiveaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um usuário tenta acessar um recurso sem estar devidamente autenticado.
 * Esta exceção é mapeada para o status HTTP 401 (Unauthorized) e é utilizada para indicar
 * que a autenticação é necessária para acessar o recurso solicitado.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    
    /**
     * Construtor que cria uma nova exceção com uma mensagem específica.
     * @param message Mensagem detalhando o motivo da não autorização
     */
    public UnauthorizedException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma nova exceção com uma mensagem e uma causa.
     * Útil quando a exceção é causada por outra exceção.
     * @param message Mensagem detalhando o motivo da não autorização
     * @param cause Exceção original que causou esta exceção
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
} 