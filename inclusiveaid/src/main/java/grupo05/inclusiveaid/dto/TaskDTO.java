package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO (Data Transfer Object) para tarefas.
 * Utilizado para transferir dados sobre tarefas executadas pelo sistema ou usuários.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Data Transfer Object for Task operations")
public class TaskDTO {
    public interface Create {}
    public interface Update {}

    /**
     * Identificador único da tarefa.
     */
    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    /**
     * Nome da tarefa.
     */
    @NotBlank(groups = {Create.class, Update.class})
    @Schema(description = "Title of the task", example = "Implement voice command feature", required = true)
    private String name;

    /**
     * Descrição da tarefa.
     */
    @NotBlank(groups = {Create.class, Update.class})
    @Schema(description = "Detailed description of the task", example = "Add voice command support for basic navigation")
    private String description;

    /**
     * Status da tarefa.
     */
    @NotNull(message = "Status is required")
    @Schema(description = "Current status of the task", example = "IN_PROGRESS", required = true)
    private String status;

    /**
     * Data e hora de criação da tarefa.
     */
    @Schema(description = "Creation date of the task")
    private String createdAt;

    /**
     * Data e hora de conclusão da tarefa.
     */
    @Schema(description = "Completion date of the task")
    private String completedAt;

    /**
     * Identificador do usuário responsável pela tarefa.
     */
    @NotNull(groups = {Create.class, Update.class})
    @Schema(description = "ID of the user responsible for the task", example = "1")
    private Long responsibleId;
} 