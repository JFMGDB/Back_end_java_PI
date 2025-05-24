package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name; // e.g. ROLE_USER, ROLE_ADMIN
}