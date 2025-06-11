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
 * Controlador responsável pela autenticação e registro de usuários no sistema InclusiveAID.
 * Gerencia o processo de login e cadastro de novos usuários, fornecendo endpoints seguros
 * para autenticação e geração de tokens JWT.
 * 
 * Este controlador implementa as operações básicas de autenticação, incluindo:
 * - Login de usuários
 * - Geração de tokens JWT
 * - Validação de credenciais
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication and registration")
public class AuthController {
    private final AuthService authService;

    /**
     * Autentica um usuário no sistema e retorna um token JWT para autenticação subsequente.
     * 
     * Este endpoint recebe as credenciais do usuário (email e senha) e, se válidas,
     * retorna um token JWT que deve ser incluído no cabeçalho Authorization das
     * requisições subsequentes.
     * 
     * @param req Objeto contendo as credenciais do usuário (email e senha)
     * @return ResponseEntity contendo o token JWT e informações do usuário autenticado
     * @throws UnauthorizedException se as credenciais forem inválidas
     * @throws ValidationException se o formato da requisição for inválido
     */
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