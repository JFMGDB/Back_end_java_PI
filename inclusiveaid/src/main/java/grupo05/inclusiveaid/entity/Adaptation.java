package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma adaptação no sistema InclusiveAID.
 * Esta entidade define as diferentes adaptações que podem ser aplicadas
 * para melhorar a acessibilidade do sistema para usuários com deficiência.
 * Cada adaptação está associada a um tipo específico de deficiência
 * e pode ser personalizada para cada usuário.
 */
@Entity
@Table(name = "adaptations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Adaptation {
  /**
   * Identificador único da adaptação no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome da adaptação.
   * Identifica a adaptação de forma única no sistema.
   */
  private String name;

  /**
   * Descrição detalhada da adaptação.
   * Explica como a adaptação funciona e seu propósito.
   */
  private String description;

  /**
   * Tipo de deficiência associado à adaptação.
   * Define para qual tipo de deficiência a adaptação é mais adequada.
   */
  @ManyToOne @JoinColumn(name="disability_type_id")
  private DisabilityType disabilityType;
}