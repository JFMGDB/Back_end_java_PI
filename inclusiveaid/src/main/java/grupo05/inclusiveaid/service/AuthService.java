package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * Serviço de autenticação.
 */
public interface AuthService extends UserDetailsService {
  AuthResponse authenticate(AuthRequest req);
  DaoAuthenticationProvider authenticationProvider();
}