package grupo05.inclusiveaid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma permissão no sistema InclusiveAID.
 * Esta entidade define as permissões que podem ser atribuídas aos usuários,
 * controlando o acesso a diferentes funcionalidades e recursos do sistema.
 * As permissões são utilizadas para implementar o controle de acesso
 * baseado em papéis (RBAC - Role-Based Access Control).
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
public class Permissions {
    
    /**
     * Identificador único da permissão no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nome único da permissão.
     * Utilizado como identificador da permissão no sistema.
     * Deve ser único para evitar duplicidade.
     */
    @Column(nullable = false, unique = true)
    private String name;
    
    /**
     * Descrição detalhada da permissão.
     * Explica o propósito e escopo da permissão.
     */
    @Column(nullable = false)
    private String description;
    
    /**
     * Indica se a permissão está ativa no sistema.
     * Permite desativar temporariamente permissões sem removê-las.
     */
    @Column(nullable = false)
    private Boolean active;
}
