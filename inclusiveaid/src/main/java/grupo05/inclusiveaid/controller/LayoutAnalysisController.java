package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.service.LayoutAnalysisService;
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
 * Controlador responsável pelo gerenciamento de análises de layout no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar e excluir análises de layout para melhorar
 * a acessibilidade das interfaces do sistema.
 * 
 * Este controlador permite:
 * - Criar novas análises de layout
 * - Consultar análises existentes
 * - Listar todas as análises de forma paginada
 * - Excluir análises (requer permissões específicas)
 * 
 * As análises de layout são utilizadas para identificar e sugerir melhorias
 * na acessibilidade das interfaces do sistema.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/layout-analysis")
@RequiredArgsConstructor
@Tag(name = "Análise de Layout", description = "APIs para gerenciamento de análises de layout no sistema AID")
public class LayoutAnalysisController {
    private final LayoutAnalysisService svc;

    /**
     * Cria uma nova análise de layout no sistema.
     * 
     * Este endpoint permite criar uma análise de layout para avaliar e melhorar
     * a acessibilidade de uma interface específica. A análise inclui recomendações
     * para tornar a interface mais acessível.
     * 
     * @param dto Dados da análise de layout a ser criada
     * @return ResponseEntity contendo os dados da análise criada
     * @throws ValidationException se os dados da análise forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Criar análise de layout",
        description = "Cria uma nova análise de layout para avaliar a acessibilidade da interface"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Análise de layout criada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LayoutAnalysisDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados da análise estão inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<LayoutAnalysisDTO> create(
        @Parameter(description = "Dados da análise de layout para criar", required = true)
        @Validated(LayoutAnalysisDTO.Create.class) @RequestBody LayoutAnalysisDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    /**
     * Recupera uma análise de layout específica pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de uma análise de layout específica,
     * incluindo as recomendações de acessibilidade e o status da análise.
     * 
     * @param id ID da análise de layout a ser recuperada
     * @return ResponseEntity contendo os dados da análise
     * @throws ResourceNotFoundException se a análise não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Obter análise de layout por ID",
        description = "Recupera uma análise de layout específica pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Análise de layout recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LayoutAnalysisDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Análise de layout não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<LayoutAnalysisDTO> get(
        @Parameter(description = "ID da análise de layout para recuperar", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    /**
     * Lista todas as análises de layout do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todas as análises de layout registradas,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de análises
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Listar todas as análises de layout",
        description = "Recupera uma lista paginada de todas as análises de layout no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de análises de layout recuperada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @GetMapping
    public ResponseEntity<Page<LayoutAnalysisDTO>> list(
        @Parameter(description = "Número da página (começa em 0)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Número de itens por página", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    /**
     * Exclui uma análise de layout do sistema.
     * 
     * Este endpoint permite a exclusão permanente de uma análise de layout.
     * Requer permissões específicas para realizar a operação.
     * 
     * @param id ID da análise de layout a ser excluída
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se a análise não for encontrada
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir
     */
    @Operation(
        summary = "Excluir análise de layout",
        description = "Remove uma análise de layout do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Análise de layout excluída com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Análise de layout não encontrada"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Proibido - Usuário não tem as permissões necessárias"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID da análise de layout para excluir", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
