package grupo05.inclusiveaid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um responsável no sistema InclusiveAID.
 * Esta entidade é utilizada para gerenciar pessoas ou agentes que são
 * responsáveis por tarefas e assistência aos usuários com deficiência.
 * Um responsável pode ser um cuidador, profissional de saúde ou
 * um agente de IA designado.
 */
@Entity
@Table(name = "responsible")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responsible {
    /**
     * Identificador único do responsável no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do responsável.
     * Campo obrigatório para identificação.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email do responsável.
     * Deve ser único no sistema e utilizado para comunicação.
     */
    @Column(nullable = false, unique = true)
    private String email;
    
    /**
     * Senha criptografada do responsável.
     * Utilizada para autenticação no sistema.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Número de telefone do responsável.
     * Utilizado para contato em caso de emergência.
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Usuário associado ao responsável.
     * Relação um-para-um que permite que o responsável
     * também seja um usuário do sistema.
     */
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}