package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um tipo de deficiência no sistema InclusiveAID.
 * Esta entidade define os diferentes tipos de deficiência que o sistema
 * pode acomodar, permitindo a personalização de adaptações e recursos
 * específicos para cada tipo de necessidade.
 */
@Entity
@Table(name = "disability_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DisabilityType {
  /**
   * Identificador único do tipo de deficiência no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome do tipo de deficiência.
   * Identifica a categoria de deficiência (ex: visual, auditiva, motora).
   */
  private String name;

  /**
   * Descrição detalhada do tipo de deficiência.
   * Fornece informações sobre as características e necessidades
   * específicas deste tipo de deficiência.
   */
  private String description;
}