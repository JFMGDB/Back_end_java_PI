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
 * Serviço responsável por operações de autenticação e registro de usuários.
 * <p>
 * Implementa a emissão de JWT, autenticação via Spring Security e carregamento
 * de {@link UserDetails} a partir do e-mail cadastrado.
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
    // Setter injetado tardiamente para evitar dependência circular
    this.authManager = authManager;
  }

  /**
   * Autentica um usuário com base nas credenciais informadas e gera um token JWT.
   *
   * @param req objeto contendo e-mail e senha para autenticação
   * @return {@link AuthResponse} contendo o token JWT válido
   * @throws UsernameNotFoundException se o usuário não for encontrado
   */
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

  /**
   * Realiza login do usuário e devolve um token JWT.
   *
   * @param request credenciais de login
   * @return {@link LoginResponse} com token JWT
   */
  @Override
  public LoginResponse login(LoginRequest request) {
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepo.findByEmail(request.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    String token = jwtService.generateToken(user);
    return new LoginResponse(token);
  }

  /**
   * Registra um novo usuário na base de dados.
   *
   * @param request dados do usuário a ser criado
   * @throws RuntimeException caso o e-mail já esteja em uso
   */
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

  /**
   * Carrega {@link UserDetails} pelo e-mail (username).
   *
   * @param username e-mail do usuário
   * @return detalhes do usuário para autenticação
   * @throws UsernameNotFoundException caso o usuário não seja encontrado
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
  }
}