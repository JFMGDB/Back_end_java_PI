package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma sugestão de adaptação no sistema InclusiveAID.
 * Esta entidade armazena sugestões geradas pelo sistema para melhorar
 * a acessibilidade de layouts e interfaces, baseadas em análises
 * de acessibilidade realizadas.
 */
@Entity
@Table(name = "suggestions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Suggestion {
  /**
   * Identificador único da sugestão no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Análise de layout associada à sugestão.
   * Referência à análise que gerou esta sugestão de adaptação.
   */
  @ManyToOne @JoinColumn(name="layout_analysis_id")
  private LayoutAnalysis analysis;

  /**
   * Mensagem da sugestão.
   * Contém a descrição da sugestão de adaptação ou melhoria
   * para aumentar a acessibilidade.
   */
  private String message;
}