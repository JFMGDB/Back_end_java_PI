package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "voice_commands")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VoiceCommand {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne @JoinColumn(name="session_id")
  private Session session;
  private String command;
  private String result;
  private Instant timestamp;
}
