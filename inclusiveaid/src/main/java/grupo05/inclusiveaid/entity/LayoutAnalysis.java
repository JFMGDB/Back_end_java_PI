package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Entidade que representa uma análise de layout no sistema InclusiveAID.
 * Esta entidade armazena informações sobre a análise de acessibilidade
 * de layouts de interface, identificando elementos que podem precisar
 * de adaptações para diferentes tipos de deficiência.
 */
@Entity
@Table(name = "layout_analyses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LayoutAnalysis {
  /**
   * Identificador único da análise no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Sessão à qual a análise está associada.
   * Permite rastrear análises dentro de uma sessão específica de uso.
   */
  @ManyToOne @JoinColumn(name="session_id")
  private Session session;

  /**
   * Detalhes da análise de layout.
   * Armazenado como texto para permitir conteúdo extenso,
   * incluindo identificação de problemas e sugestões de adaptação.
   */
  @Column(columnDefinition="TEXT")
  private String details;

  /**
   * Momento em que a análise foi realizada.
   * Definido automaticamente como o momento atual da criação.
   */
  @Column(nullable = false)
  @Builder.Default
  private Instant timestamp = Instant.now();
}