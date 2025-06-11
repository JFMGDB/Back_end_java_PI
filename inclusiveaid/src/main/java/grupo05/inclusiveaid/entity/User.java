package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entidade que representa um usuário no sistema InclusiveAID.
 * Implementa a interface UserDetails do Spring Security para gerenciamento de autenticação e autorização.
 * Esta entidade armazena informações pessoais do usuário, suas preferências de acessibilidade
 * e configurações de adaptação para diferentes tipos de deficiência.
 */
@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User implements UserDetails {

  /**
   * Identificador único do usuário no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome completo do usuário.
   * Campo obrigatório que não pode estar em branco.
   */
  @NotBlank(message = "O nome é obrigatório")
  @Column(nullable = false)
  private String name;

  /**
   * Email do usuário, utilizado como nome de usuário para login.
   * Deve ser único no sistema e seguir o formato válido de email.
   */
  @NotBlank(message = "O email é obrigatório")
  @Email(message = "Email inválido")
  @Column(nullable = false, unique = true)
  private String email;

  /**
   * Senha criptografada do usuário.
   * Campo obrigatório que não pode estar em branco.
   */
  @NotBlank(message = "A senha é obrigatória")
  @Column(nullable = false)
  private String password;

  /**
   * Indica se a conta do usuário está ativa no sistema.
   * Por padrão, é definido como true quando um novo usuário é criado.
   */
  @Column(nullable = false)
  @Builder.Default
  private Boolean active = true;

  /**
   * Tipo de deficiência principal do usuário.
   * Armazenado como string para flexibilidade.
   */
  @Column
  private String disabilityType;

  /**
   * Observações e informações adicionais sobre o usuário.
   * Armazenado como texto para permitir conteúdo extenso.
   */
  @Column(columnDefinition = "TEXT")
  private String notes;

  /**
   * Perfil de acesso do usuário no sistema.
   * Define as permissões e níveis de acesso (ex: ROLE_USER, ROLE_ADMIN).
   */
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  /**
   * Conjunto de tipos de deficiência associados ao usuário.
   * Permite que um usuário tenha múltiplos tipos de deficiência.
   */
  @ManyToMany
  @JoinTable(
    name = "user_disability_types",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "disability_type_id")
  )
  @Builder.Default
  private Set<DisabilityType> disabilityTypes = new HashSet<>();

  /**
   * Lista de configurações de adaptação personalizadas do usuário.
   * Cada configuração define como o sistema deve se adaptar às necessidades específicas do usuário.
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<UserAdaptationSetting> adaptations = new ArrayList<>();

  /**
   * Retorna as autoridades (roles) do usuário para o Spring Security.
   * @return Lista contendo a autoridade baseada no papel do usuário
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getName()));
  }

  /**
   * Retorna o email do usuário como nome de usuário para autenticação.
   * @return Email do usuário
   */
  @Override
  public String getUsername() {
    return email;
  }

  /**
   * Verifica se a conta do usuário não está expirada.
   * @return true, pois não implementamos expiração de conta
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Verifica se a conta do usuário não está bloqueada.
   * @return true, pois não implementamos bloqueio de conta
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Verifica se as credenciais do usuário não estão expiradas.
   * @return true, pois não implementamos expiração de credenciais
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Verifica se a conta do usuário está habilitada.
   * @return Status atual da conta (true = ativa, false = inativa)
   */
  @Override
  public boolean isEnabled() {
    return active;
  }
}