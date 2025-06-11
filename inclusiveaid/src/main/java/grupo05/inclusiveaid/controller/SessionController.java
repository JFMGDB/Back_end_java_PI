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
 * Controlador responsável pelo gerenciamento de sessões de usuários no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar e encerrar sessões de usuários.
 * 
 * Este controlador permite:
 * - Criar novas sessões para usuários
 * - Consultar sessões existentes
 * - Listar todas as sessões de forma paginada
 * - Encerrar sessões ativas
 * 
 * As sessões são utilizadas para controlar o acesso dos usuários ao sistema
 * e manter o estado de suas interações com as funcionalidades de acessibilidade.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@Tag(name = "Sessões", description = "APIs para gerenciamento de sessões de usuários no sistema AID")
public class SessionController {
    private final SessionService svc;

    /**
     * Cria uma nova sessão para um usuário no sistema.
     * 
     * Este endpoint permite iniciar uma nova sessão para um usuário,
     * registrando informações como data/hora de início e configurações
     * de acessibilidade específicas para a sessão.
     * 
     * @param dto Dados da sessão a ser criada
     * @return ResponseEntity contendo os dados da sessão criada
     * @throws ValidationException se os dados da sessão forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Recupera uma sessão específica pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de uma sessão específica,
     * incluindo informações sobre o usuário e suas configurações de acessibilidade.
     * 
     * @param id ID da sessão a ser recuperada
     * @return ResponseEntity contendo os dados da sessão
     * @throws ResourceNotFoundException se a sessão não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Lista todas as sessões do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todas as sessões registradas,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de sessões
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
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

    /**
     * Encerra uma sessão ativa no sistema.
     * 
     * Este endpoint permite encerrar uma sessão específica,
     * registrando a data/hora de término e finalizando as
     * interações do usuário com o sistema.
     * 
     * @param id ID da sessão a ser encerrada
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se a sessão não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para encerrar a sessão
     */
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