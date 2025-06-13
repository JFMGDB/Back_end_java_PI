package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa uma tarefa no sistema InclusiveAID.
 * Esta entidade é utilizada para gerenciar tarefas que podem ser automatizadas
 * ou auxiliadas pelo agente de IA, especialmente para usuários com deficiência.
 * As tarefas podem ser atribuídas a responsáveis e possuem um ciclo de vida
 * gerenciado através do status.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tarefas")
public class Task {
    /**
     * Identificador único da tarefa no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título da tarefa.
     * Deve ser único e descritivo, com tamanho entre 3 e 100 caracteres.
     */
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;
    
    /**
     * Descrição detalhada da tarefa.
     * Deve conter informações suficientes para compreensão e execução,
     * com tamanho entre 10 e 500 caracteres.
     */
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Column(nullable = false)
    private String description;

    /**
     * Status atual da tarefa.
     * Indica em qual fase do ciclo de vida a tarefa se encontra
     * (ex: pendente, em andamento, concluída).
     */
    @Column(nullable = false)
    private String status;
    
    /**
     * Responsável pela execução da tarefa.
     * Pode ser um usuário ou um agente de IA designado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;
}
