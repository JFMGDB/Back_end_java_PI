package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Entidade que representa um feedback de usuário no sistema InclusiveAID.
 * Esta entidade armazena comentários e sugestões dos usuários sobre
 * a experiência de uso do sistema, permitindo melhorias contínuas
 * na acessibilidade e usabilidade.
 */
@Entity
@Table(name = "feedbacks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Feedback {
  /**
   * Identificador único do feedback no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Usuário que enviou o feedback.
   * Referência ao usuário que forneceu o comentário ou sugestão.
   */
  @ManyToOne @JoinColumn(name="user_id")
  private User user;

  /**
   * Mensagem do feedback.
   * Contém o comentário, sugestão ou crítica do usuário
   * sobre sua experiência com o sistema.
   */
  private String message;

  /**
   * Momento em que o feedback foi enviado.
   * Registra a data e hora do envio do feedback.
   */
  private Instant timestamp;
}