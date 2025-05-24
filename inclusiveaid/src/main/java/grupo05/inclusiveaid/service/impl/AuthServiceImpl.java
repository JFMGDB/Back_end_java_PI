package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.security.JwtUtil;
import grupo05.inclusiveaid.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * Implementação de AuthService.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthenticationManager authManager;
  private final UserRepository userRepo;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder encoder;

  @Override
  public AuthResponse authenticate(AuthRequest req) {
    // autentica pelo Spring
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
    // carrega usuário para extrair dados
    User u = userRepo.findByEmail(req.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    String token = jwtUtil.generateToken(u.getEmail());
    return new AuthResponse(token);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User u = userRepo.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    return org.springframework.security.core.userdetails.User
      .withUsername(u.getEmail())
      .password(u.getPassword())
      .authorities(u.getRole().getName())
      .build();
  }

  @Override
public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider p = new DaoAuthenticationProvider(this);
    p.setPasswordEncoder(encoder);
    return p;
}
}