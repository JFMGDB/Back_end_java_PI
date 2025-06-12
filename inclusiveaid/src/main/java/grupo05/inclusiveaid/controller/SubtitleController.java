package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.service.SubtitleService;
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
 * Controlador responsável pelo gerenciamento de legendas de acessibilidade no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir legendas para usuários com deficiência auditiva.
 */
@RestController
@RequestMapping("/api/subtitles")
@RequiredArgsConstructor
@Tag(name = "Legenda", description = "APIs para gerenciamento de legendas de acessibilidade no sistema AID")
public class SubtitleController {
    private final SubtitleService svc;

    /**
     * Cria uma nova legenda de acessibilidade.
     *
     * @param dto Dados da legenda a ser criada
     * @return ResponseEntity contendo a legenda criada
     */
    @Operation(
        summary = "Criar legenda",
        description = "Cria um novo registro de legenda para acessibilidade"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Legenda criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da legenda estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<SubtitleDTO> create(
        @Parameter(description = "Dados da legenda para criar", required = true)
        @Validated(SubtitleDTO.Create.class) @RequestBody SubtitleDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera uma legenda específica pelo ID.
     *
     * @param id ID da legenda
     * @return ResponseEntity contendo a legenda
     */
    @Operation(
        summary = "Obter legenda por ID",
        description = "Recupera uma legenda específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Legenda recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Legenda não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubtitleDTO> get(
        @Parameter(description = "ID da legenda para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todas as legendas do sistema de forma paginada.
     *
     * @param page Número da página
     * @param size Quantidade de itens por página
     * @return Página de legendas
     */
    @Operation(
        summary = "Listar todas as legendas",
        description = "Recupera uma lista paginada de todas as legendas no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de legendas recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<SubtitleDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Atualiza uma legenda existente.
     *
     * @param id ID da legenda
     * @param dto Novos dados da legenda
     * @return Legenda atualizada
     */
    @Operation(
        summary = "Atualizar legenda",
        description = "Atualiza uma legenda existente com novo texto"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Legenda atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da legenda estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Legenda não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SubtitleDTO> update(
        @Parameter(description = "ID da legenda para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da legenda", required = true)
        @Validated(SubtitleDTO.Update.class) @RequestBody SubtitleDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    /**
     * Exclui uma legenda do sistema.
     *
     * @param id ID da legenda
     */
    @Operation(
        summary = "Excluir legenda",
        description = "Remove uma legenda do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Legenda excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Legenda não encontrada"
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
        @Parameter(description = "ID da legenda para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
