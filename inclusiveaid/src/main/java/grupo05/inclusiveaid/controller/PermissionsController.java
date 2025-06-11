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
 * Controlador responsável pelo gerenciamento de permissões no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir permissões de usuários.
 * 
 * Este controlador implementa o controle de acesso baseado em permissões (RBAC),
 * permitindo:
 * - Criar novas permissões para usuários
 * - Consultar permissões existentes
 * - Listar todas as permissões de forma paginada
 * - Atualizar permissões existentes
 * - Excluir permissões (requer permissões específicas)
 * 
 * Todas as operações são protegidas por autenticação e autorização,
 * garantindo que apenas usuários com as permissões adequadas possam
 * gerenciar as permissões do sistema.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permissões", description = "APIs para gerenciamento de permissões de usuários no sistema AID")
public class PermissionsController {
    private final PermissionsService svc;

    /**
     * Cria uma nova permissão no sistema.
     * 
     * Este endpoint permite criar uma nova permissão para um usuário específico.
     * A permissão define quais ações o usuário pode realizar no sistema.
     * 
     * @param dto Dados da permissão a ser criada
     * @return ResponseEntity contendo os dados da permissão criada
     * @throws ValidationException se os dados da permissão forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para criar permissões
     */
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

    /**
     * Recupera uma permissão específica pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de uma permissão específica,
     * incluindo o usuário associado e as ações permitidas.
     * 
     * @param id ID da permissão a ser recuperada
     * @return ResponseEntity contendo os dados da permissão
     * @throws ResourceNotFoundException se a permissão não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Lista todas as permissões do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todas as permissões registradas,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de permissões
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para listar permissões
     */
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

    /**
     * Atualiza uma permissão existente.
     * 
     * Este endpoint permite modificar os dados de uma permissão existente,
     * como as ações permitidas ou o usuário associado.
     * 
     * @param id ID da permissão a ser atualizada
     * @param dto Novos dados da permissão
     * @return ResponseEntity contendo os dados atualizados da permissão
     * @throws ValidationException se os dados da permissão forem inválidos
     * @throws ResourceNotFoundException se a permissão não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para atualizar permissões
     */
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

    /**
     * Exclui uma permissão do sistema.
     * 
     * Este endpoint permite a exclusão permanente de uma permissão.
     * Requer permissões específicas para realizar a operação.
     * 
     * @param id ID da permissão a ser excluída
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se a permissão não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir permissões
     */
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
