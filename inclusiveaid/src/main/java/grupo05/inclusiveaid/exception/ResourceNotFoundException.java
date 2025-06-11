package grupo05.inclusiveaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso solicitado não é encontrado no sistema.
 * Esta exceção é mapeada para o status HTTP 404 (Not Found) e é utilizada para indicar
 * que o recurso solicitado não existe ou não está disponível.
 * 
 * Exemplos de uso:
 * - Usuário não encontrado
 * - Arquivo não encontrado
 * - Registro não encontrado no banco de dados
 * - Recurso externo indisponível
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constrói uma exceção com uma mensagem personalizada.
     * A mensagem deve ser clara e informativa, indicando qual recurso não foi encontrado
     * e, se possível, o identificador utilizado na busca.
     * 
     * @param message Mensagem detalhando o recurso não encontrado (ex: "Usuário com ID 123 não encontrado")
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}