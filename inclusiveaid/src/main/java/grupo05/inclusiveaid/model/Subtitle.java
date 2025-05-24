package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "subtitles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subtitle {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne @JoinColumn(name="session_id")
  private Session session;
  private String text;
  private Instant timestamp;
}