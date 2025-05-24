package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adaptations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Adaptation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  @ManyToOne @JoinColumn(name="disability_type_id")
  private DisabilityType disabilityType;
}