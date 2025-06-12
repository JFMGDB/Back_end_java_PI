package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;

/**
 * DTO (Data Transfer Object) para tarefas do sistema.
 * Utilizado para transferir dados relacionados às tarefas criadas e gerenciadas pelos usuários.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Schema(description = "Data Transfer Object for Task operations")
public class TaskDTO {
    
    /**
     * Identificador único da tarefa.
     */
    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;
    
    /**
     * Título da tarefa.
     * Não pode estar em branco.
     */
    @NotBlank(message = "Title is required")
    @Schema(description = "Title of the task", example = "Implement voice command feature", required = true)
    private String title;
    
    /**
     * Descrição detalhada da tarefa.
     */
    @Schema(description = "Detailed description of the task", example = "Add voice command support for basic navigation")
    private String description;
    
    @NotNull(message = "Status is required")
    @Schema(description = "Current status of the task", example = "IN_PROGRESS", required = true)
    private String status;
    
    @Schema(description = "ID of the user responsible for the task", example = "1")
    private Long responsibleId;

    /**
     * Data e hora de criação da tarefa.
     */
    private Instant createdAt;

    /**
     * Data e hora de conclusão da tarefa.
     */
    private Instant completedAt;
} 