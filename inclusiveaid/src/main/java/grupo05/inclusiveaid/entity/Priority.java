package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entidade que representa uma prioridade de tarefa.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "priorities")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nível da prioridade (ex: LOW, MEDIUM, HIGH).
     */
    @NotBlank(message = "Level is required")
    @Size(max = 20, message = "Level must be up to 20 characters")
    @Column(nullable = false, unique = true, length = 20)
    private String level;
}
