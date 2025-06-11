package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AuthRequest;
import grupo05.inclusiveaid.dto.AuthResponse;
import grupo05.inclusiveaid.dto.LoginRequest;
import grupo05.inclusiveaid.dto.LoginResponse;
import grupo05.inclusiveaid.dto.RegisterRequest;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.security.JwtService;
import grupo05.inclusiveaid.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementação de AuthService.
 */
@Service
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepo;
  private final JwtService jwtService;
  private final PasswordEncoder encoder;
  private AuthenticationManager authManager;

  public AuthServiceImpl(UserRepository userRepo, JwtService jwtService, PasswordEncoder encoder) {
    this.userRepo = userRepo;
    this.jwtService = jwtService;
    this.encoder = encoder;
  }

  @Autowired
  @Lazy
  public void setAuthManager(AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  @Override
  public AuthResponse authenticate(AuthRequest req) {
    // autentica pelo Spring
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
    // carrega usuário para extrair dados
    User u = userRepo.findByEmail(req.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    String token = jwtService.generateToken(u);
    return new AuthResponse(token);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepo.findByEmail(request.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    String token = jwtService.generateToken(user);
    return new LoginResponse(token);
  }

  @Override
  public void register(RegisterRequest request) {
    if (userRepo.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email já registrado");
    }
    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(encoder.encode(request.getPassword()));
    userRepo.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
  }
}