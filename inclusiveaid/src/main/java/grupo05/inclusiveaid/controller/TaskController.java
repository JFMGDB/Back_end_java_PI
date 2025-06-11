package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.TaskDTO;
import grupo05.inclusiveaid.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por gerenciar as operações relacionadas às tarefas.
 * Fornece endpoints REST para criar, ler, atualizar e deletar tarefas.
 */
@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "APIs for managing tasks in the AID system")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    private final TaskService taskService;

    @Operation(
        summary = "Get all tasks",
        description = "Retrieves a paginated list of all tasks in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all tasks",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @GetMapping
    public ResponseEntity<Page<TaskDTO>> listAll(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Page size", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(taskService.listAll(page, size));
    }

    @Operation(
        summary = "Get task by ID",
        description = "Retrieves a specific task by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the task",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Task not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(
        @Parameter(description = "ID of the task to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @Operation(
        summary = "Create new task",
        description = "Creates a new task in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the task",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The task data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<TaskDTO> create(
        @Parameter(description = "Task data to create", required = true)
        @Valid @RequestBody TaskDTO taskDTO
    ) {
        return ResponseEntity.ok(taskService.create(taskDTO));
    }

    @Operation(
        summary = "Update task",
        description = "Updates an existing task's information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the task",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Task not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The task data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(
        @Parameter(description = "ID of the task to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated task data", required = true)
        @Valid @RequestBody TaskDTO taskDTO
    ) {
        return ResponseEntity.ok(taskService.update(id, taskDTO));
    }

    @Operation(
        summary = "Delete task",
        description = "Deletes a task from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the task"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Task not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID of the task to delete", required = true)
        @PathVariable Long id
    ) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
