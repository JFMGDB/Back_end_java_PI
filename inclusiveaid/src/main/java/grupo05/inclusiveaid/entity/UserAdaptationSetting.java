package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma configuração de adaptação de usuário no sistema InclusiveAID.
 * Esta entidade gerencia as preferências de adaptação de cada usuário,
 * permitindo personalizar como o sistema se adapta às necessidades
 * específicas de cada pessoa com deficiência.
 */
@Entity
@Table(name = "user_adaptation_settings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAdaptationSetting {
  /**
   * Identificador único da configuração no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Usuário associado à configuração.
   * Referência ao usuário que possui estas preferências de adaptação.
   */
  @ManyToOne @JoinColumn(name="user_id")
  private User user;

  /**
   * Adaptação associada à configuração.
   * Referência à adaptação específica que pode ser habilitada ou desabilitada.
   */
  @ManyToOne @JoinColumn(name="adaptation_id")
  private Adaptation adaptation;

  /**
   * Indica se a adaptação está habilitada para o usuário.
   * Controla se a adaptação específica está ativa para este usuário.
   */
  private boolean enabled;
}