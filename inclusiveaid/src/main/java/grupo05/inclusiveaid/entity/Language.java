package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entidade que representa um idioma suportado pelo sistema.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "languages")
public class Language {
    /**
     * Identificador único do idioma.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Código ISO do idioma (ex: pt, en, es).
     */
@NotBlank(message = "Code is required") 
@Size(min = 2, max = 10, message = "Code must be between 2 and 10 
characters") 
@Column(nullable = false, unique = true, length = 10) 
private String code;
    /**
     * Nome do idioma.
     */
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    @Column(nullable = false, length = 100)
    private String name;
}