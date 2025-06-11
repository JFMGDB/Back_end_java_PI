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
 * Controlador responsável pelo gerenciamento de adaptações de acessibilidade no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir adaptações.
 * 
 * Este controlador permite:
 * - Cadastrar novas adaptações de acessibilidade no sistema
 * - Consultar adaptações existentes
 * - Listar todas as adaptações de forma paginada
 * - Atualizar informações de adaptações
 * - Remover adaptações do sistema
 * 
 * As adaptações são configurações específicas que melhoram a acessibilidade
 * do sistema para diferentes tipos de deficiência, como:
 * - Adaptações visuais (alto contraste, tamanho de fonte)
 * - Adaptações auditivas (legendas, descrições de áudio)
 * - Adaptações motoras (navegação por teclado, comandos de voz)
 * - Adaptações cognitivas (simplificação de interface, assistentes)
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/adaptations")
@RequiredArgsConstructor
@Tag(name = "Adaptações", description = "APIs para gerenciamento de adaptações de acessibilidade no sistema AID")
public class AdaptationController {
    private final AdaptationService svc;

    /**
     * Cria uma nova adaptação de acessibilidade no sistema.
     * 
     * Este endpoint permite cadastrar uma nova adaptação,
     * incluindo informações como tipo, configurações e
     * requisitos específicos de acessibilidade.
     * 
     * @param dto Dados da adaptação a ser criada
     * @return ResponseEntity contendo os dados da adaptação criada
     * @throws ValidationException se os dados da adaptação forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para criar adaptações
     */
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

    /**
     * Recupera uma adaptação específica pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de uma adaptação específica,
     * incluindo suas configurações e requisitos de acessibilidade.
     * 
     * @param id ID da adaptação a ser recuperada
     * @return ResponseEntity contendo os dados da adaptação
     * @throws ResourceNotFoundException se a adaptação não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Lista todas as adaptações do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todas as adaptações registradas,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de adaptações
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Atualiza uma adaptação existente no sistema.
     * 
     * Este endpoint permite modificar as informações de uma adaptação,
     * como configurações e requisitos de acessibilidade.
     * 
     * @param id ID da adaptação a ser atualizada
     * @param dto Novos dados da adaptação
     * @return ResponseEntity contendo os dados atualizados da adaptação
     * @throws ValidationException se os dados da adaptação forem inválidos
     * @throws ResourceNotFoundException se a adaptação não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para atualizar adaptações
     */
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

    /**
     * Remove uma adaptação do sistema.
     * 
     * Este endpoint permite excluir permanentemente uma adaptação,
     * removendo-a do sistema e suas associações com usuários.
     * 
     * @param id ID da adaptação a ser excluída
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se a adaptação não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir adaptações
     */
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