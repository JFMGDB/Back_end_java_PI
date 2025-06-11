package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Entidade que representa uma legenda no sistema InclusiveAID.
 * Esta entidade armazena as legendas geradas automaticamente pelo sistema
 * para auxiliar usuários com deficiência auditiva, fornecendo transcrições
 * de áudio e diálogos em tempo real.
 */
@Entity
@Table(name = "subtitles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subtitle {
  /**
   * Identificador único da legenda no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Sessão à qual a legenda está associada.
   * Permite rastrear legendas dentro de uma sessão específica de uso.
   */
  @ManyToOne @JoinColumn(name="session_id")
  private Session session;

  /**
   * Texto da legenda.
   * Contém a transcrição do áudio ou diálogo.
   */
  private String text;

  /**
   * Momento em que a legenda foi gerada.
   * Utilizado para sincronização com o conteúdo de áudio.
   */
  private Instant timestamp;
}