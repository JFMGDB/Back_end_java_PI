package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.service.VoiceCommandService;
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
 * Controlador responsável pelo gerenciamento de comandos de voz no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir comandos de voz.
 */
@RestController
@RequestMapping("/api/voice-commands")
@RequiredArgsConstructor
@Tag(name = "Comando de Voz", description = "APIs para gerenciamento de comandos de voz no sistema AID")
public class VoiceCommandController {
    private final VoiceCommandService svc;

    /**
     * Cria um novo comando de voz no sistema.
     *
     * @param dto Dados do comando de voz a ser criado
     * @return ResponseEntity contendo o comando criado
     */
    @Operation(
        summary = "Criar comando de voz",
        description = "Cria um novo registro de comando de voz no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Comando de voz criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do comando de voz estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<VoiceCommandDTO> create(
        @Parameter(description = "Dados do comando de voz para criar", required = true)
        @Validated(VoiceCommandDTO.Create.class) @RequestBody VoiceCommandDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera um comando de voz específico pelo ID.
     *
     * @param id ID do comando de voz
     * @return ResponseEntity contendo o comando de voz
     */
    @Operation(
        summary = "Obter comando de voz por ID",
        description = "Recupera um comando de voz específico pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Comando de voz recuperado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Comando de voz não encontrado"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<VoiceCommandDTO> get(
        @Parameter(description = "ID do comando de voz para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todos os comandos de voz do sistema de forma paginada.
     *
     * @param page Número da página
     * @param size Quantidade de itens por página
     * @return Página de comandos de voz
     */
    @Operation(
        summary = "Listar todos os comandos de voz",
        description = "Recupera uma lista paginada de todos os comandos de voz no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de comandos de voz recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<VoiceCommandDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Atualiza um comando de voz existente.
     *
     * @param id ID do comando de voz
     * @param dto Novos dados do comando de voz
     * @return Comando de voz atualizado
     */
    @Operation(
        summary = "Atualizar comando de voz",
        description = "Atualiza um comando de voz existente no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Comando de voz atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do comando de voz estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Comando de voz não encontrado"
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
    @PutMapping("/{id}")
    public ResponseEntity<VoiceCommandDTO> update(
        @Parameter(description = "ID do comando de voz para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados do comando de voz", required = true)
        @Validated(VoiceCommandDTO.Update.class) @RequestBody VoiceCommandDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    /**
     * Exclui um comando de voz do sistema.
     *
     * @param id ID do comando de voz
     */
    @Operation(
        summary = "Excluir comando de voz",
        description = "Remove um comando de voz do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Comando de voz excluído com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Comando de voz não encontrado"
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
        @Parameter(description = "ID do comando de voz para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}