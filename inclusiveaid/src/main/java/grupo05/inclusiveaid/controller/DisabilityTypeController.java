package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.DisabilityTypeDTO;
import grupo05.inclusiveaid.service.DisabilityTypeService;
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
 * Controlador responsável pelo gerenciamento de tipos de deficiência no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir tipos de deficiência.
 * 
 * Este controlador permite:
 * - Cadastrar novos tipos de deficiência no sistema
 * - Consultar tipos de deficiência existentes
 * - Listar todos os tipos de deficiência de forma paginada
 * - Atualizar informações de tipos de deficiência
 * - Remover tipos de deficiência do sistema
 * 
 * Os tipos de deficiência são utilizados para categorizar e identificar
 * as diferentes necessidades de acessibilidade dos usuários do sistema.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/disability-types")
@RequiredArgsConstructor
@Tag(name = "Tipos de Deficiência", description = "APIs para gerenciamento de tipos de deficiência no sistema AID")
public class DisabilityTypeController {
    private final DisabilityTypeService svc;

    /**
     * Cria um novo tipo de deficiência no sistema.
     * 
     * Este endpoint permite cadastrar um novo tipo de deficiência,
     * incluindo informações como nome, descrição e requisitos
     * específicos de acessibilidade.
     * 
     * @param dto Dados do tipo de deficiência a ser criado
     * @return ResponseEntity contendo os dados do tipo de deficiência criado
     * @throws ValidationException se os dados do tipo de deficiência forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para criar tipos de deficiência
     */
    @Operation(
        summary = "Criar tipo de deficiência",
        description = "Cria um novo tipo de deficiência no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de deficiência criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = DisabilityTypeDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do tipo de deficiência estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para criar tipos de deficiência"
        )
    })
    @PostMapping
    public ResponseEntity<DisabilityTypeDTO> create(
        @Parameter(description = "Dados do tipo de deficiência para criar", required = true)
        @Validated(DisabilityTypeDTO.Create.class) @RequestBody DisabilityTypeDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera um tipo de deficiência específico pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de um tipo de deficiência específico,
     * incluindo suas características e requisitos de acessibilidade.
     * 
     * @param id ID do tipo de deficiência a ser recuperado
     * @return ResponseEntity contendo os dados do tipo de deficiência
     * @throws ResourceNotFoundException se o tipo de deficiência não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Obter tipo de deficiência por ID",
        description = "Recupera um tipo de deficiência específico pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de deficiência recuperado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = DisabilityTypeDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de deficiência não encontrado"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisabilityTypeDTO> get(
        @Parameter(description = "ID do tipo de deficiência para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todos os tipos de deficiência do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todos os tipos de deficiência registrados,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de tipos de deficiência
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Listar todos os tipos de deficiência",
        description = "Recupera uma lista paginada de todos os tipos de deficiência no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de tipos de deficiência recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<DisabilityTypeDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Atualiza um tipo de deficiência existente no sistema.
     * 
     * Este endpoint permite modificar as informações de um tipo de deficiência,
     * como nome, descrição e requisitos de acessibilidade.
     * 
     * @param id ID do tipo de deficiência a ser atualizado
     * @param dto Novos dados do tipo de deficiência
     * @return ResponseEntity contendo os dados atualizados do tipo de deficiência
     * @throws ValidationException se os dados do tipo de deficiência forem inválidos
     * @throws ResourceNotFoundException se o tipo de deficiência não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para atualizar tipos de deficiência
     */
    @Operation(
        summary = "Atualizar tipo de deficiência",
        description = "Atualiza um tipo de deficiência existente com novos dados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de deficiência atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = DisabilityTypeDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do tipo de deficiência estão inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de deficiência não encontrado"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para atualizar tipos de deficiência"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<DisabilityTypeDTO> update(
        @Parameter(description = "ID do tipo de deficiência para atualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Novos dados do tipo de deficiência", required = true)
        @Validated(DisabilityTypeDTO.Update.class) @RequestBody DisabilityTypeDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    /**
     * Remove um tipo de deficiência do sistema.
     * 
     * Este endpoint permite excluir permanentemente um tipo de deficiência,
     * removendo-o do sistema e suas associações com usuários.
     * 
     * @param id ID do tipo de deficiência a ser excluído
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se o tipo de deficiência não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir tipos de deficiência
     */
    @Operation(
        summary = "Excluir tipo de deficiência",
        description = "Remove um tipo de deficiência do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tipo de deficiência excluído com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de deficiência não encontrado"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem permissão para excluir tipos de deficiência"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID do tipo de deficiência para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}