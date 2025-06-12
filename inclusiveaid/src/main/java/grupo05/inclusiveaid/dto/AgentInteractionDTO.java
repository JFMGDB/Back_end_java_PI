package grupo05.inclusiveaid.dto;

import grupo05.inclusiveaid.enums.InteractionType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO para interação de agentes de IA com o usuário.
 * Utilizado para transferir dados de interações entre agentes de IA e usuários, como comandos, respostas e contexto.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentInteractionDTO {
    /**
     * Identificador do agente de IA.
     */
    private Long id;
    /**
     * Identificador do agente de IA.
     */
    private Long agentId;
    /**
     * Identificador do usuário.
     */
    private Long userId;
    /**
     * Timestamp da interação.
     */
    private LocalDateTime timestamp;
    /**
     * Tipo de interação.
     */
    private InteractionType type;
    /**
     * Comando enviado ao agente.
     */
    private String action;
    /**
     * Resposta do agente ao comando.
     */
    private String response;
    /**
     * Indica se a interação foi bem-sucedida.
     */
    private boolean successful;
} 