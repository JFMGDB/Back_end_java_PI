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

@RestController
@RequestMapping("/api/responsible")
@RequiredArgsConstructor
@Tag(name = "Responsável", description = "Endpoints para gerenciamento de usuários responsáveis que auxiliam pessoas com deficiência")
public class ResponsibleController {
    private final ResponsibleService responsibleService;

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
        List<ResponsibleDTO> responsibles = responsibleService.getAllResponsibles();
        if (responsibles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(responsibles);
    }

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
        return ResponseEntity.ok(responsibleService.getResponsibleById(id));
    }

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
        return ResponseEntity.ok(responsibleService.createResponsible(responsibleDTO));
    }

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
        return ResponseEntity.ok(responsibleService.updateResponsible(id, responsibleDTO));
    }

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
        responsibleService.deleteResponsible(id);
        return ResponseEntity.noContent().build();
    }
}
