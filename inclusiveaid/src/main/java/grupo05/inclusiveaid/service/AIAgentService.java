package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.AIAgentDTO;
import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import java.util.List;

public interface AIAgentService {
    AIAgentDTO createAgent(AIAgentDTO agentDTO);
    AIAgentDTO updateAgent(Long id, AIAgentDTO agentDTO);
    void deleteAgent(Long id);
    AIAgentDTO getAgent(Long id);
    List<AIAgentDTO> getAllAgents();
    List<AIAgentDTO> getActiveAgents();
    
    // AI Interaction methods
    AgentInteractionDTO processVoiceCommand(Long agentId, Long userId, String command);
    AgentInteractionDTO analyzeLayout(Long agentId, Long userId, String layoutData);
    AgentInteractionDTO generateSubtitle(Long agentId, Long userId, String audioText);
    AgentInteractionDTO describeImage(Long agentId, Long userId, String imageData);
    AgentInteractionDTO adaptLayout(Long agentId, Long userId, String layoutData);
    AgentInteractionDTO automateTask(Long agentId, Long userId, String taskDescription);
    
    // AI Model management
    void updateAIModel(Long agentId, String modelType, String modelVersion);
    void trainAIModel(Long agentId, String trainingData);
} 