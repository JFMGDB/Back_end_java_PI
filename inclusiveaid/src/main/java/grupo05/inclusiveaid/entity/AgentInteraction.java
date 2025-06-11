package grupo05.inclusiveaid.entity;

import grupo05.inclusiveaid.enums.InteractionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Representa uma interação do agente de IA com um usuário.
 * Esta entidade registra todas as interações entre o agente de IA e os usuários,
 * incluindo ações realizadas, respostas geradas e adaptações aplicadas.
 * O registro dessas interações permite análise de uso, melhoria do sistema
 * e personalização das respostas do agente.
 */
@Entity
@Table(name = "agent_interactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AgentInteraction {

    /**
     * Identificador único da interação no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Agente de IA que realizou a interação.
     * Referência ao agente responsável pela ação.
     */
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private AIAgent agent;

    /**
     * Usuário que participou da interação.
     * Referência ao usuário que recebeu a ação do agente.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Data e hora em que a interação ocorreu.
     * Definido automaticamente no momento da criação do registro.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * Tipo da interação realizada.
     * Define a categoria da ação (ex: comando de voz, adaptação visual, etc.).
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InteractionType type;

    /**
     * Descrição da ação realizada pelo agente.
     * Detalha o que foi executado durante a interação.
     */
    @Column(nullable = false)
    private String action;

    /**
     * Resposta ou resultado gerado pelo agente.
     * Registra o feedback ou resultado da ação realizada.
     */
    @Column
    private String response;

    /**
     * Contexto em que a interação ocorreu.
     * Armazena informações adicionais sobre o ambiente ou situação.
     */
    @Column
    private String context;

    /**
     * Indica se a interação foi bem-sucedida.
     * Usado para análise de desempenho e melhoria do sistema.
     */
    @Column
    private boolean successful;

    /**
     * Define automaticamente o timestamp da interação.
     * Executado antes de persistir uma nova interação.
     */
    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
} 