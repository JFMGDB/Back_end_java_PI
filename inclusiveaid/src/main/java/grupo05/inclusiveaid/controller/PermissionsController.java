package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.PermissionsDTO;
import grupo05.inclusiveaid.service.PermissionsService;
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
 * Controlador para gerenciamento de permissões no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir permissões de usuários.
 */
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permissões", description = "APIs para gerenciamento de permissões de usuários no sistema AID")
public class PermissionsController {
    private final PermissionsService svc;

    @Operation(
        summary = "Criar permissão",
        description = "Cria uma nova permissão para um usuário no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Permissão criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PermissionsDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da permissão estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para criar permissões"
        )
    })
    @PostMapping
    public ResponseEntity<PermissionsDTO> create(
        @Parameter(description = "Dados da permissão para criar", required = true)
        @Validated(PermissionsDTO.Create.class) @RequestBody PermissionsDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Obter permissão por ID",
        description = "Recupera uma permissão específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Permissão recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PermissionsDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Permissão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PermissionsDTO> get(
        @Parameter(description = "ID da permissão para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "Listar todas as permissões",
        description = "Recupera uma lista paginada de todas as permissões no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de permissões recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para listar permissões"
        )
    })
    @GetMapping
    public ResponseEntity<Page<PermissionsDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Atualizar permissão",
        description = "Atualiza uma permissão existente com novos dados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Permissão atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PermissionsDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da permissão estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Permissão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para atualizar permissões"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<PermissionsDTO> update(
        @Parameter(description = "ID da permissão para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da permissão", required = true)
        @Validated(PermissionsDTO.Update.class) @RequestBody PermissionsDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @Operation(
        summary = "Excluir permissão",
        description = "Remove uma permissão do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Permissão excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Permissão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para excluir permissões"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da permissão para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
