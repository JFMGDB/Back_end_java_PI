package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "layout_analyses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LayoutAnalysis {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne @JoinColumn(name="session_id")
  private Session session;
  @Column(columnDefinition="TEXT")
  private String details;
}