package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.ResponsibleDTO;
import grupo05.inclusiveaid.service.ResponsibleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelo gerenciamento de usuários responsáveis no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir usuários responsáveis.
 * 
 * Este controlador permite:
 * - Cadastrar novos usuários responsáveis no sistema
 * - Consultar usuários responsáveis existentes
 * - Listar todos os usuários responsáveis
 * - Atualizar informações de usuários responsáveis
 * - Remover usuários responsáveis do sistema
 * 
 * Os usuários responsáveis são pessoas que auxiliam pessoas com deficiência,
 * podendo ser cuidadores, familiares ou profissionais de saúde.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/responsible")
@RequiredArgsConstructor
@Tag(name = "Responsável", description = "Endpoints para gerenciamento de usuários responsáveis que auxiliam pessoas com deficiência")
public class ResponsibleController {
    private final ResponsibleService responsibleService;

    /**
     * Lista todos os usuários responsáveis do sistema.
     * 
     * Este endpoint permite consultar todos os usuários responsáveis registrados,
     * retornando uma lista com seus dados básicos.
     * 
     * @return ResponseEntity contendo a lista de usuários responsáveis
     * @throws InternalServerErrorException se ocorrer um erro interno no servidor
     */
    @GetMapping
    @Operation(summary = "Listar todos os responsáveis", description = "Recupera a lista de todos os usuários responsáveis no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Responsáveis recuperados com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResponsibleDTO.class))),
        @ApiResponse(responseCode = "204", description = "Nenhum responsável encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ResponsibleDTO>> getAllResponsible() {
        List<ResponsibleDTO> responsibles = responsibleService.listAll(0, Integer.MAX_VALUE).getContent();
        if (responsibles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(responsibles);
    }

    /**
     * Recupera um usuário responsável específico pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de um usuário responsável específico,
     * incluindo suas informações pessoais e contato.
     * 
     * @param id ID do usuário responsável a ser recuperado
     * @return ResponseEntity contendo os dados do usuário responsável
     * @throws ResourceNotFoundException se o usuário responsável não for encontrado
     * @throws InternalServerErrorException se ocorrer um erro interno no servidor
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar responsável por ID", description = "Recupera um usuário responsável específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Responsável recuperado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResponsibleDTO.class))),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ResponsibleDTO> getResponsibleById(
        @Parameter(description = "ID do usuário responsável a ser recuperado", example = "1", required = true)
        @PathVariable Long id) {
        return ResponseEntity.ok(responsibleService.getById(id));
    }

    /**
     * Cria um novo usuário responsável no sistema.
     * 
     * Este endpoint permite cadastrar um novo usuário responsável,
     * incluindo informações como nome, e-mail, telefone e associação
     * com um usuário do sistema.
     * 
     * @param responsibleDTO Dados do usuário responsável a ser criado
     * @return ResponseEntity contendo os dados do usuário responsável criado
     * @throws ValidationException se os dados do usuário responsável forem inválidos
     * @throws ConflictException se o e-mail já estiver em uso
     * @throws InternalServerErrorException se ocorrer um erro interno no servidor
     */
    @PostMapping
    @Operation(summary = "Criar responsável", description = "Cria um novo usuário responsável no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Responsável criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResponsibleDTO.class),
                examples = @ExampleObject(value = """
                    {
                        "id": 1,
                        "name": "João Silva",
                        "email": "joao.silva@exemplo.com",
                        "phone": "+5511999999999",
                        "userId": 1
                    }"""))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "409", description = "E-mail já existe"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ResponsibleDTO> createResponsible(
        @Parameter(description = "Dados do usuário responsável", required = true)
        @Validated(ResponsibleDTO.Create.class) @RequestBody ResponsibleDTO responsibleDTO) {
        return ResponseEntity.ok(responsibleService.create(responsibleDTO));
    }

    /**
     * Atualiza um usuário responsável existente no sistema.
     * 
     * Este endpoint permite modificar as informações de um usuário responsável,
     * como dados pessoais e contato.
     * 
     * @param id ID do usuário responsável a ser atualizado
     * @param responsibleDTO Novos dados do usuário responsável
     * @return ResponseEntity contendo os dados atualizados do usuário responsável
     * @throws ValidationException se os dados do usuário responsável forem inválidos
     * @throws ResourceNotFoundException se o usuário responsável não for encontrado
     * @throws ConflictException se o e-mail já estiver em uso
     * @throws InternalServerErrorException se ocorrer um erro interno no servidor
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar responsável", description = "Atualiza as informações de um usuário responsável existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Responsável atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResponsibleDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado"),
        @ApiResponse(responseCode = "409", description = "E-mail já existe"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ResponsibleDTO> updateResponsible(
        @Parameter(description = "ID do usuário responsável a ser atualizado", example = "1", required = true)
        @PathVariable Long id,
        @Parameter(description = "Dados atualizados do usuário responsável", required = true)
        @Validated(ResponsibleDTO.Update.class) @RequestBody ResponsibleDTO responsibleDTO) {
        return ResponseEntity.ok(responsibleService.update(id, responsibleDTO));
    }

    /**
     * Remove um usuário responsável do sistema.
     * 
     * Este endpoint permite excluir permanentemente um usuário responsável,
     * removendo-o do sistema e suas associações com usuários.
     * 
     * @param id ID do usuário responsável a ser excluído
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se o usuário responsável não for encontrado
     * @throws InternalServerErrorException se ocorrer um erro interno no servidor
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir responsável", description = "Remove um usuário responsável do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Responsável excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteResponsible(
        @Parameter(description = "ID do usuário responsável a ser excluído", example = "1", required = true)
        @PathVariable Long id) {
        responsibleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
