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
 * Entidade que representa um usuário no sistema.
 * Contém informações básicas como nome, email e senha.
 */
@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome é obrigatório")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "O email é obrigatório")
  @Email(message = "Email inválido")
  @Column(nullable = false, unique = true)
  private String email;

  @NotBlank(message = "A senha é obrigatória")
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Builder.Default
  private Boolean active = true;

  @Column
  private String disabilityType;

  @Column(columnDefinition = "TEXT")
  private String notes;

  // Perfil de acesso (ROLE_USER, ROLE_ADMIN, etc.)
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  // Relação muitos-para-muitos com tipos de deficiência
  @ManyToMany
  @JoinTable(
    name = "user_disability_types",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "disability_type_id")
  )
  @Builder.Default
  private Set<DisabilityType> disabilityTypes = new HashSet<>();

  // Configurações de adaptação definidas pelo usuário
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<UserAdaptationSetting> adaptations = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getName()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}