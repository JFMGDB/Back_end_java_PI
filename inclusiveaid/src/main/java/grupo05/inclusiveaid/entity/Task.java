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
 * Entidade que representa uma tarefa no sistema.
 * Mapeada para a tabela 'tarefas' no banco de dados.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tarefas")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;
}
