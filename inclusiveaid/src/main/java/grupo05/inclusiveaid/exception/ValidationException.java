package grupo05.inclusiveaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando ocorre uma falha na validação de dados.
 * Esta exceção é mapeada para o status HTTP 400 (Bad Request) e é utilizada para indicar
 * que os dados fornecidos não atendem aos requisitos de validação do sistema.
 * Pode ser lançada em casos como:
 * - Dados obrigatórios ausentes
 * - Formato de dados inválido
 * - Valores fora dos limites permitidos
 * - Regras de negócio violadas
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    
    /**
     * Construtor que cria uma nova exceção com uma mensagem específica.
     * @param message Mensagem detalhando o motivo da falha na validação
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Construtor que cria uma nova exceção com uma mensagem e uma causa.
     * Útil quando a exceção é causada por outra exceção.
     * @param message Mensagem detalhando o motivo da falha na validação
     * @param cause Exceção original que causou esta exceção
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
} 