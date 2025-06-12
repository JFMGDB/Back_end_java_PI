package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para comandos de voz.
 * Utilizado para transferir dados relacionados aos comandos de voz reconhecidos
 * pelo sistema, facilitando a interação por voz para usuários com deficiência.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
public class VoiceCommandDTO {
    /**
     * Interface para validação na criação de um novo comando de voz.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de um comando de voz existente.
     */
    public interface Update {}
    
    /**
     * Identificador único do comando de voz.
     */
    private Long id;

    /**
     * Identificador da sessão onde o comando foi executado.
     * Não pode ser nulo na criação.
     */
    @NotNull(groups = Create.class)
    private Long sessionId;

    /**
     * Comando de voz reconhecido pelo sistema.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String command;

    /**
     * Resultado da execução do comando de voz.
     */
    private String result;

    /**
     * Data e hora em que o comando foi executado.
     */
    private Instant timestamp;
}