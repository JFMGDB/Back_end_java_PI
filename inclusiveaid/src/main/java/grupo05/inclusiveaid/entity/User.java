package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

/**
 * Representa um usuário da aplicação.
 * Um usuário possui:
 * - nome, email (único) e senha (BCrypt)
 * - um perfil (Role)
 * - múltiplos tipos de deficiência (ManyToMany)
 * - diversas configurações de adaptação (OneToMany)
 */
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

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
}