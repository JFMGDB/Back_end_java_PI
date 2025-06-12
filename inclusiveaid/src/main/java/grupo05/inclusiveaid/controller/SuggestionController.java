package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import grupo05.inclusiveaid.service.SuggestionService;
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
 * Controlador responsável pelo gerenciamento de sugestões de acessibilidade no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir sugestões para melhoria da acessibilidade digital.
 */
@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
@Tag(name = "Sugestão", description = "APIs para gerenciamento de sugestões de acessibilidade no sistema AID")
public class SuggestionController {
    private final SuggestionService svc;

    /**
     * Cria uma nova sugestão de acessibilidade.
     *
     * @param dto Dados da sugestão a ser criada
     * @return ResponseEntity contendo a sugestão criada
     */
    @Operation(
        summary = "Criar sugestão",
        description = "Cria uma nova sugestão para melhoria de recursos de acessibilidade"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sugestão criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da sugestão estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<SuggestionDTO> create(
        @Parameter(description = "Dados da sugestão para criar", required = true)
        @Validated(SuggestionDTO.Create.class) @RequestBody SuggestionDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera uma sugestão específica pelo ID.
     *
     * @param id ID da sugestão
     * @return ResponseEntity contendo a sugestão
     */
    @Operation(
        summary = "Obter sugestão por ID",
        description = "Recupera uma sugestão específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sugestão recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sugestão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuggestionDTO> get(
        @Parameter(description = "ID da sugestão para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todas as sugestões do sistema de forma paginada.
     *
     * @param page Número da página
     * @param size Quantidade de itens por página
     * @return Página de sugestões
     */
    @Operation(
        summary = "Listar todas as sugestões",
        description = "Recupera uma lista paginada de todas as sugestões no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de sugestões recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<SuggestionDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Atualiza uma sugestão existente.
     *
     * @param id ID da sugestão
     * @param dto Novos dados da sugestão
     * @return Sugestão atualizada
     */
    @Operation(
        summary = "Atualizar sugestão",
        description = "Atualiza uma sugestão existente com novos dados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sugestão atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da sugestão estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sugestão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SuggestionDTO> update(
        @Parameter(description = "ID da sugestão para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da sugestão", required = true)
        @Validated(SuggestionDTO.Update.class) @RequestBody SuggestionDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    /**
     * Exclui uma sugestão do sistema.
     *
     * @param id ID da sugestão
     */
    @Operation(
        summary = "Excluir sugestão",
        description = "Remove uma sugestão do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Sugestão excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sugestão não encontrada"
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
        @Parameter(description = "ID da sugestão para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}