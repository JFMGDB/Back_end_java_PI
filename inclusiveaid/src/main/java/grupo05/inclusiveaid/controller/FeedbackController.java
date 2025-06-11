package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.FeedbackDTO;
import grupo05.inclusiveaid.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelo gerenciamento de feedback dos usuários no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar e excluir feedback sobre recursos de acessibilidade.
 * 
 * Este controlador permite que os usuários:
 * - Enviem feedback sobre recursos de acessibilidade
 * - Consultem feedback existente
 * - Listem todos os feedbacks de forma paginada
 * - Excluam feedbacks (requer permissões específicas)
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "APIs for managing user feedback in the AID system")
public class FeedbackController {
    private final FeedbackService svc;

    /**
     * Cria um novo registro de feedback no sistema.
     * 
     * Este endpoint permite que usuários enviem feedback sobre recursos de acessibilidade.
     * O feedback é validado antes de ser armazenado no sistema.
     * 
     * @param dto Dados do feedback a ser criado
     * @return ResponseEntity contendo os dados do feedback criado
     * @throws ValidationException se os dados do feedback forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Create feedback",
        description = "Creates a new feedback entry from a user about accessibility features"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = FeedbackDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The feedback data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<FeedbackDTO> create(
        @Parameter(description = "Feedback data to create", required = true)
        @Validated(FeedbackDTO.Create.class) @RequestBody FeedbackDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera um feedback específico pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de um feedback específico,
     * incluindo o conteúdo da mensagem e informações do usuário.
     * 
     * @param id ID do feedback a ser recuperado
     * @return ResponseEntity contendo os dados do feedback
     * @throws ResourceNotFoundException se o feedback não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Get feedback by ID",
        description = "Retrieves a specific feedback entry by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = FeedbackDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> get(
        @Parameter(description = "ID of the feedback to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todos os feedbacks do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todos os feedbacks registrados,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de feedbacks
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "List all feedback",
        description = "Retrieves a paginated list of all feedback entries in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<FeedbackDTO>> list(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Exclui um feedback do sistema.
     * 
     * Este endpoint permite a exclusão permanente de um feedback.
     * Requer permissões específicas para realizar a operação.
     * 
     * @param id ID do feedback a ser excluído
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se o feedback não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir
     */
    @Operation(
        summary = "Delete feedback",
        description = "Deletes a feedback entry from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the feedback"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback not found"
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
        @Parameter(description = "ID of the feedback to delete", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}