package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entidade simples que representa uma Categoria no sistema.
 * Usada para agrupar ou categorizar diferentes recursos.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categories")
public class Category {
    /**
     * Identificador único da categoria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nome da categoria.
     */
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be up to 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}