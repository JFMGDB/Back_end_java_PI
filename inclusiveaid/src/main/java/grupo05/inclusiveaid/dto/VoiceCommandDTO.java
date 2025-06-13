package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para comandos de voz.
 * Utilizado para transferir dados relacionados ao processamento e reconhecimento de comandos de voz.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class VoiceCommandDTO {
    /**
     * Identificador único do comando de voz.
     */
    private Long id;
    
    /**
     * Comando de voz processado.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String command;
    
    /**
     * Identificador do usuário que executou o comando.
     */
    @NotNull(groups = {Create.class, Update.class})
    private Long userId;
    
    /**
     * Nível de confiança do reconhecimento (0.0 a 1.0).
     */
    private Double confidence;
    
    /**
     * Status do processamento do comando.
     */
    private String status;
    
    /**
     * Data e hora de execução do comando.
     */
    private String timestamp;
    
    public interface Create {}
    public interface Update {}
}