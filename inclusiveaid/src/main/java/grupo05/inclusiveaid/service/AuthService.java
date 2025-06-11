package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import grupo05.inclusiveaid.dto.LoginRequest;
import grupo05.inclusiveaid.dto.LoginResponse;
import grupo05.inclusiveaid.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Serviço de autenticação.
 */
public interface AuthService extends UserDetailsService {
  AuthResponse authenticate(AuthRequest request);
  LoginResponse login(LoginRequest request);
  void register(RegisterRequest request);
}