package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "disability_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DisabilityType {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
}