package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entidade que representa uma Tag genérica que pode ser associada a outros recursos
 * (ex.: tarefas, sugestões). Mantém apenas identificador e nome único.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {
    /**
     * Identificador único da tag.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da tag.
     */
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be up to 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
