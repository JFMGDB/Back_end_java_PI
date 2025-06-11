package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um papel (role) no sistema InclusiveAID.
 * Esta entidade define os diferentes níveis de acesso e permissões
 * que podem ser atribuídos aos usuários do sistema, implementando
 * o controle de acesso baseado em papéis (RBAC).
 */
@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
  /**
   * Identificador único do papel no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome do papel.
   * Define o nível de acesso (ex: ROLE_USER, ROLE_ADMIN).
   * Segue o padrão de nomenclatura do Spring Security.
   */
  private String name; // e.g. ROLE_USER, ROLE_ADMIN
}