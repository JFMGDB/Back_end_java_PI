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
 * Controlador responsável pelo gerenciamento das tarefas no sistema AID.
 * Fornece endpoints REST para criar, recuperar, atualizar e deletar tarefas.
 */
@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Tag(name = "Gerenciamento de Tarefas", description = "APIs para gerenciamento de tarefas no sistema AID")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    private final TaskService taskService;

    /**
     * Lista todas as tarefas do sistema de forma paginada.
     *
     * @param page Número da página
     * @param size Quantidade de itens por página
     * @return Página de tarefas
     */
    @Operation(
        summary = "Listar todas as tarefas",
        description = "Recupera uma lista paginada de todas as tarefas no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefas recuperadas com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não possui as permissões necessárias"
        )
    })
    @GetMapping
    public ResponseEntity<Page<TaskDTO>> listAll(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(taskService.listAll(page, size));
    }

    /**
     * Recupera uma tarefa específica pelo ID.
     *
     * @param id ID da tarefa
     * @return ResponseEntity contendo a tarefa
     */
    @Operation(
        summary = "Obter tarefa por ID",
        description = "Recupera uma tarefa específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(
        @Parameter(description = "ID da tarefa para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    /**
     * Cria uma nova tarefa no sistema.
     *
     * @param taskDTO Dados da tarefa a ser criada
     * @return ResponseEntity contendo a tarefa criada
     */
    @Operation(
        summary = "Criar nova tarefa",
        description = "Cria uma nova tarefa no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da tarefa estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<TaskDTO> create(
        @Parameter(description = "Dados da tarefa para criar", required = true)
        @Valid @RequestBody TaskDTO taskDTO
    ) {
        return ResponseEntity.ok(taskService.create(taskDTO));
    }

    /**
     * Atualiza uma tarefa existente.
     *
     * @param id ID da tarefa
     * @param taskDTO Novos dados da tarefa
     * @return Tarefa atualizada
     */
    @Operation(
        summary = "Atualizar tarefa",
        description = "Atualiza as informações de uma tarefa existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = TaskDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da tarefa estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(
        @Parameter(description = "ID da tarefa para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da tarefa", required = true)
        @Valid @RequestBody TaskDTO taskDTO
    ) {
        return ResponseEntity.ok(taskService.update(id, taskDTO));
    }

    /**
     * Exclui uma tarefa do sistema.
     *
     * @param id ID da tarefa
     */
    @Operation(
        summary = "Excluir tarefa",
        description = "Remove uma tarefa do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tarefa excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não possui as permissões necessárias"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da tarefa para excluir", required = true)
        @PathVariable Long id
    ) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
