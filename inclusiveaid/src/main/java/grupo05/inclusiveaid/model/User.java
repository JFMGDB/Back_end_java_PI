package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  @ManyToOne @JoinColumn(name = "role_id")
  private Role role;

  @ManyToMany
  @JoinTable(
    name = "user_disability_types",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "disability_type_id")
  )
  private Set<DisabilityType> disabilityTypes = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserAdaptationSetting> adaptations = new ArrayList<>();
}