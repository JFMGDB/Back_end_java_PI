package grupo05.inclusiveaid.dto;

import grupo05.inclusiveaid.enums.InteractionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentInteractionDTO {
    private Long id;
    private Long agentId;
    private Long userId;
    private LocalDateTime timestamp;
    private InteractionType type;
    private String action;
    private String response;
    private boolean successful;
} 