package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.service.AdaptationService;
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
 * Controlador para gerenciamento de adaptações de acessibilidade no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir adaptações para diferentes tipos de deficiência.
 */
@RestController
@RequestMapping("/api/adaptations")
@RequiredArgsConstructor
@Tag(name = "Adaptações", description = "APIs para gerenciamento de adaptações de acessibilidade no sistema AID")
public class AdaptationController {
    private final AdaptationService svc;

    @Operation(
        summary = "Criar adaptação",
        description = "Cria uma nova adaptação de acessibilidade no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Adaptação criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AdaptationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da adaptação estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para criar adaptações"
        )
    })
    @PostMapping
    public ResponseEntity<AdaptationDTO> create(
        @Parameter(description = "Dados da adaptação para criar", required = true)
        @Validated(AdaptationDTO.Create.class) @RequestBody AdaptationDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Obter adaptação por ID",
        description = "Recupera uma adaptação específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Adaptação recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AdaptationDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Adaptação não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdaptationDTO> get(
        @Parameter(description = "ID da adaptação para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "Listar todas as adaptações",
        description = "Recupera uma lista paginada de todas as adaptações no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de adaptações recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<AdaptationDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Atualizar adaptação",
        description = "Atualiza uma adaptação existente com novos dados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Adaptação atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AdaptationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da adaptação estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Adaptação não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para atualizar adaptações"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdaptationDTO> update(
        @Parameter(description = "ID da adaptação para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da adaptação", required = true)
        @Validated(AdaptationDTO.Update.class) @RequestBody AdaptationDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @Operation(
        summary = "Excluir adaptação",
        description = "Remove uma adaptação do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Adaptação excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Adaptação não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para excluir adaptações"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da adaptação para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}