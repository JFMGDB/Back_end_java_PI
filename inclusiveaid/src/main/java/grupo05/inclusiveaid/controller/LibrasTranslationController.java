package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import grupo05.inclusiveaid.service.LibrasTranslationService;
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
 * Controlador responsável pelo gerenciamento de traduções em Libras no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir traduções em Libras.
 * 
 * Este controlador permite:
 * - Cadastrar novas traduções em Libras
 * - Consultar traduções existentes
 * - Listar todas as traduções de forma paginada
 * - Atualizar traduções existentes
 * - Excluir traduções (requer permissões específicas)
 * 
 * As traduções em Libras são utilizadas para tornar o conteúdo do sistema
 * acessível para usuários surdos ou com deficiência auditiva.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/libras-translations")
@RequiredArgsConstructor
@Tag(name = "Tradução em Libras", description = "APIs para gerenciamento de traduções em Libras no sistema AID")
@SecurityRequirement(name = "bearerAuth")
public class LibrasTranslationController {
    private final LibrasTranslationService service;

    /**
     * Cria uma nova tradução em Libras no sistema.
     * 
     * Este endpoint permite cadastrar uma nova tradução em Libras,
     * incluindo o texto original e sua tradução correspondente.
     * 
     * @param dto Dados da tradução a ser criada
     * @return ResponseEntity contendo os dados da tradução criada
     * @throws ValidationException se os dados da tradução forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Criar tradução em Libras",
        description = "Cria uma nova tradução em Libras no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tradução criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da tradução estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<LibrasTranslationDTO> create(
        @Parameter(description = "Dados da tradução para criar", required = true)
        @Valid @RequestBody LibrasTranslationDTO dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    /**
     * Recupera uma tradução em Libras específica pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de uma tradução específica,
     * incluindo o texto original e sua tradução em Libras.
     * 
     * @param id ID da tradução a ser recuperada
     * @return ResponseEntity contendo os dados da tradução
     * @throws ResourceNotFoundException se a tradução não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Obter tradução em Libras por ID",
        description = "Recupera uma tradução em Libras específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tradução recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tradução não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<LibrasTranslationDTO> getById(
        @Parameter(description = "ID da tradução para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Lista todas as traduções em Libras do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todas as traduções registradas,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de traduções
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Listar todas as traduções em Libras",
        description = "Recupera uma lista paginada de todas as traduções em Libras no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de traduções recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<LibrasTranslationDTO>> listAll(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listAll(page, size));
    }

    /**
     * Atualiza uma tradução em Libras existente.
     * 
     * Este endpoint permite modificar os dados de uma tradução existente,
     * como o texto original ou sua tradução em Libras.
     * 
     * @param id ID da tradução a ser atualizada
     * @param dto Novos dados da tradução
     * @return ResponseEntity contendo os dados atualizados da tradução
     * @throws ValidationException se os dados da tradução forem inválidos
     * @throws ResourceNotFoundException se a tradução não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Atualizar tradução em Libras",
        description = "Atualiza uma tradução em Libras existente no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tradução atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da tradução estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tradução não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<LibrasTranslationDTO> update(
        @Parameter(description = "ID da tradução para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados da tradução", required = true)
        @Valid @RequestBody LibrasTranslationDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    /**
     * Exclui uma tradução em Libras do sistema.
     * 
     * Este endpoint permite a exclusão permanente de uma tradução.
     * Requer permissões específicas para realizar a operação.
     * 
     * @param id ID da tradução a ser excluída
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se a tradução não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Excluir tradução em Libras",
        description = "Remove uma tradução em Libras do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tradução excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tradução não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da tradução para excluir", required = true)
        @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 