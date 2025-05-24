package grupo05.inclusiveaid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_adaptation_settings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAdaptationSetting {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne @JoinColumn(name="user_id")
  private User user;
  @ManyToOne @JoinColumn(name="adaptation_id")
  private Adaptation adaptation;
  private boolean enabled;
}