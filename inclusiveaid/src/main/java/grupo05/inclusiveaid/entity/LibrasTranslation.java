package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma tradução de texto para Libras no sistema InclusiveAID.
 * Esta entidade armazena o mapeamento entre textos em português e suas respectivas
 * animações em Libras, permitindo a inclusão de usuários surdos através da
 * tradução automática de conteúdo textual para linguagem de sinais.
 */
@Entity
@Table(name = "libras_translations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LibrasTranslation {

    /**
     * Identificador único da tradução no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Texto original em português a ser traduzido.
     * Armazenado com limite de 1000 caracteres para otimização.
     */
    @Column(nullable = false, length = 1000)
    private String originalText;

    /**
     * URL da animação do avatar em Libras.
     * Aponta para o recurso de vídeo ou animação que representa
     * a tradução do texto em linguagem de sinais.
     */
    @Column(nullable = false)
    private String avatarAnimationUrl;

    /**
     * Indica se a tradução está ativa no sistema.
     * Permite desativar temporariamente traduções sem removê-las.
     */
    @Column(nullable = false)
    private boolean active;

    /**
     * Data e hora de criação da tradução.
     * Definido automaticamente no momento da criação.
     */
    @Column(nullable = false)
    private java.time.LocalDateTime createdAt;

    /**
     * Data e hora da última atualização da tradução.
     * Atualizado automaticamente quando a tradução é modificada.
     */
    @Column
    private java.time.LocalDateTime updatedAt;

    /**
     * Define automaticamente a data de criação e status ativo.
     * Executado antes de persistir uma nova tradução.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        active = true;
    }

    /**
     * Atualiza automaticamente a data de modificação.
     * Executado antes de atualizar uma tradução existente.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
} 