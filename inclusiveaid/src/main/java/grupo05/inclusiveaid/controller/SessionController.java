package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SessionDTO;
import grupo05.inclusiveaid.service.SessionService;
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
 * Controlador para gerenciamento de sessões de usuários no sistema AID.
 * Fornece endpoints para criar, recuperar, listar e encerrar sessões de usuários.
 */
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@Tag(name = "Sessões", description = "APIs para gerenciamento de sessões de usuários no sistema AID")
public class SessionController {
    private final SessionService svc;

    @Operation(
        summary = "Criar sessão",
        description = "Cria uma nova sessão para um usuário no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sessão criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SessionDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da sessão estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<SessionDTO> create(
        @Parameter(description = "Dados da sessão para criar", required = true)
        @Validated(SessionDTO.Create.class) @RequestBody SessionDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Obter sessão por ID",
        description = "Recupera uma sessão específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sessão recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SessionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sessão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> get(
        @Parameter(description = "ID da sessão para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "Listar todas as sessões",
        description = "Recupera uma lista paginada de todas as sessões no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de sessões recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<SessionDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Encerrar sessão",
        description = "Encerra uma sessão ativa no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Sessão encerrada com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sessão não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para encerrar esta sessão"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da sessão para encerrar", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}