package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.UserDTO;
import grupo05.inclusiveaid.service.UserService;
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
 * Controlador responsável pelo gerenciamento de usuários no sistema InclusiveAID.
 * Fornece endpoints REST para criar, recuperar, atualizar e excluir usuários.
 * 
 * Este controlador permite:
 * - Cadastrar novos usuários no sistema
 * - Consultar usuários existentes
 * - Listar todos os usuários de forma paginada
 * - Atualizar informações de usuários
 * - Remover usuários do sistema
 * 
 * Os usuários são entidades fundamentais do sistema, podendo ter diferentes
 * perfis e necessidades de acessibilidade associadas.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users in the AID system")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    /**
     * Lista todos os usuários do sistema de forma paginada.
     * 
     * Este endpoint permite consultar todos os usuários registrados,
     * com suporte a paginação para melhor performance.
     * 
     * @param page Número da página (começando em 0)
     * @param size Quantidade de itens por página
     * @return ResponseEntity contendo a página de usuários
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para listar usuários
     */
    @Operation(
        summary = "Get all users",
        description = "Retrieves a paginated list of all users in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all users",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @GetMapping
    public ResponseEntity<Page<UserDTO>> listAll(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Page size", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(userService.listAll(page, size));
    }

    /**
     * Recupera um usuário específico pelo seu ID.
     * 
     * Este endpoint permite consultar os detalhes de um usuário específico,
     * incluindo suas informações pessoais e configurações de acessibilidade.
     * 
     * @param id ID do usuário a ser recuperado
     * @return ResponseEntity contendo os dados do usuário
     * @throws ResourceNotFoundException se o usuário não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a specific user by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the user",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(
        @Parameter(description = "ID of the user to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getById(id));
    }

    /**
     * Cria um novo usuário no sistema.
     * 
     * Este endpoint permite cadastrar um novo usuário,
     * incluindo informações pessoais e configurações
     * de acessibilidade específicas.
     * 
     * @param userDTO Dados do usuário a ser criado
     * @return ResponseEntity contendo os dados do usuário criado
     * @throws ValidationException se os dados do usuário forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Create new user",
        description = "Creates a new user in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the user",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The user data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<UserDTO> create(
        @Parameter(description = "User data to create", required = true)
        @Valid @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.create(userDTO));
    }

    /**
     * Atualiza um usuário existente no sistema.
     * 
     * Este endpoint permite modificar as informações de um usuário,
     * como dados pessoais e configurações de acessibilidade.
     * 
     * @param id ID do usuário a ser atualizado
     * @param userDTO Novos dados do usuário
     * @return ResponseEntity contendo os dados atualizados do usuário
     * @throws ValidationException se os dados do usuário forem inválidos
     * @throws ResourceNotFoundException se o usuário não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Update user",
        description = "Updates an existing user's information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the user",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The user data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
        @Parameter(description = "ID of the user to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated user data", required = true)
        @Valid @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    /**
     * Remove um usuário do sistema.
     * 
     * Este endpoint permite excluir permanentemente um usuário,
     * removendo-o do sistema e suas associações com outros recursos.
     * 
     * @param id ID do usuário a ser excluído
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se o usuário não for encontrado
     * @throws UnauthorizedException se o usuário não estiver autenticado
     * @throws AccessDeniedException se o usuário não tiver permissão para excluir usuários
     */
    @Operation(
        summary = "Delete user",
        description = "Deletes a user from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the user"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID of the user to delete", required = true)
        @PathVariable Long id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}