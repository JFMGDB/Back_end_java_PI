package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "layout_elements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LayoutElement {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String elementType;
  private String description;
  private String xpath;
}