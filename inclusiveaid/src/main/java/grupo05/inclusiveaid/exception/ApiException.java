package grupo05.inclusiveaid.exception;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Classe que representa uma exceção padronizada da API.
 * Esta classe é utilizada para estruturar as respostas de erro da API de forma consistente,
 * fornecendo informações detalhadas sobre o erro ocorrido.
 * 
 * A estrutura inclui:
 * - Status HTTP do erro
 * - Mensagem descritiva
 * - Código de erro específico
 * - Timestamp do momento do erro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException {
    /**
     * Código de status HTTP da resposta de erro.
     * Exemplos: 400 (Bad Request), 401 (Unauthorized), 404 (Not Found), etc.
     */
    private int status;

    /**
     * Mensagem descritiva do erro, fornecendo detalhes sobre o que ocorreu
     * e, quando possível, como resolver o problema.
     */
    private String message;

    /**
     * Código de erro específico da aplicação.
     * Utilizado para identificar o tipo específico de erro e facilitar
     * o tratamento e debugging.
     */
    private String errorCode;

    /**
     * Momento exato em que o erro ocorreu.
     * Útil para rastreamento e debugging de problemas.
     */
    private LocalDateTime timestamp;
}