package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar uma tarefa.
 * Usado para transferir dados entre a camada de apresentação e a camada de serviço.
 */
@Data
@Schema(description = "Data Transfer Object for Task operations")
public class TaskDTO {
    
    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Schema(description = "Title of the task", example = "Implement voice command feature", required = true)
    private String title;
    
    @Schema(description = "Detailed description of the task", example = "Add voice command support for basic navigation")
    private String description;
    
    @NotNull(message = "Status is required")
    @Schema(description = "Current status of the task", example = "IN_PROGRESS", required = true)
    private String status;
    
    @Schema(description = "ID of the user responsible for the task", example = "1")
    private Long responsibleId;
} 