package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma tradução de texto para Libras.
 * Armazena o texto original e a URL da animação do avatar em Libras.
 */
@Entity
@Table(name = "libras_translations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LibrasTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String originalText;

    @Column(nullable = false)
    private String avatarAnimationUrl;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private java.time.LocalDateTime createdAt;

    @Column
    private java.time.LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
} 