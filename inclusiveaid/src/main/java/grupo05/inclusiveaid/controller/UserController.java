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
 * Controlador responsável por gerenciar as operações relacionadas aos usuários.
 * Fornece endpoints REST para criar, ler, atualizar e deletar usuários.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users in the AID system")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

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