package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Entidade que representa uma sessão de uso no sistema InclusiveAID.
 * Esta entidade registra o período de interação de um usuário com o sistema,
 * permitindo rastrear o tempo de uso, comandos executados e adaptações aplicadas
 * durante uma sessão específica.
 */
@Entity
@Table(name = "sessions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Session {
  /**
   * Identificador único da sessão no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Usuário associado à sessão.
   * Referência ao usuário que está utilizando o sistema.
   */
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="user_id")
  private User user;

  /**
   * Momento em que a sessão foi iniciada.
   * Registra o timestamp de início da interação.
   */
  private Instant startedAt;

  /**
   * Momento em que a sessão foi encerrada.
   * Registra o timestamp de término da interação.
   * Pode ser nulo se a sessão ainda estiver ativa.
   */
  private Instant endedAt;
}