package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import grupo05.inclusiveaid.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints para autenticação.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
    return ResponseEntity.ok(authService.authenticate(req));
  }
}