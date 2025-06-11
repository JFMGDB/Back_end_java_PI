package grupo05.inclusiveaid.entity;

import grupo05.inclusiveaid.enums.InteractionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Representa uma interação do agente de IA com um usuário.
 * Registra ações, respostas e adaptações realizadas pelo agente.
 */
@Entity
@Table(name = "agent_interactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AgentInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private AIAgent agent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InteractionType type;

    @Column(nullable = false)
    private String action;

    @Column
    private String response;

    @Column
    private String context;

    @Column
    private boolean successful;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
} 