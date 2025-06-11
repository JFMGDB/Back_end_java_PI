package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import grupo05.inclusiveaid.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela autenticação e registro de usuários.
 * Gerencia o processo de login e cadastro de novos usuários.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication and registration")
public class AuthController {
    private final AuthService authService;

    @Operation(
        summary = "Authenticate user",
        description = "Authenticates a user and returns a JWT token for subsequent requests"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully authenticated",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AuthResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format"
        )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @Parameter(description = "User credentials", required = true)
        @RequestBody AuthRequest req
    ) {
        return ResponseEntity.ok(authService.authenticate(req));
    }
}