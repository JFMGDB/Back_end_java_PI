package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AIAgentDTO;
import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import grupo05.inclusiveaid.dto.NLPConfigDTO;
import grupo05.inclusiveaid.dto.ImageProcessingConfigDTO;
import grupo05.inclusiveaid.dto.VoiceProcessingConfigDTO;
import grupo05.inclusiveaid.entity.AIAgent;
import grupo05.inclusiveaid.entity.AgentInteraction;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;
import grupo05.inclusiveaid.config.VoiceProcessingConfig;
import grupo05.inclusiveaid.enums.InteractionType;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.repository.AIAgentRepository;
import grupo05.inclusiveaid.repository.AgentInteractionRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.AIAgentService;
import grupo05.inclusiveaid.service.AIExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIAgentServiceImpl implements AIAgentService {

    private final AIAgentRepository agentRepository;
    private final AgentInteractionRepository interactionRepository;
    private final UserRepository userRepository;
    private final AIExternalService aiExternalService;

    @Override
    @Transactional
    public AIAgentDTO createAgent(AIAgentDTO agentDTO) {
        AIAgent agent = mapToEntity(agentDTO);
        agent = agentRepository.save(agent);
        return mapToDTO(agent);
    }

    @Override
    @Transactional
    public AIAgentDTO updateAgent(Long id, AIAgentDTO agentDTO) {
        AIAgent agent = agentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agent not found with id: " + id));
        
        updateAgentFromDTO(agent, agentDTO);
        agent = agentRepository.save(agent);
        return mapToDTO(agent);
    }

    @Override
    @Transactional
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public AIAgentDTO getAgent(Long id) {
        AIAgent agent = agentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agent not found with id: " + id));
        return mapToDTO(agent);
    }

    @Override
    public List<AIAgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AIAgentDTO> getActiveAgents() {
        return agentRepository.findByIsActive(true).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AgentInteractionDTO processVoiceCommand(Long agentId, Long userId, String command) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String response = aiExternalService.processVoiceCommand(command, agent.getNlpConfig());
        return createInteraction(agent, user, InteractionType.VOICE_COMMAND, command, response);
    }

    @Override
    @Transactional
    public AgentInteractionDTO analyzeLayout(Long agentId, Long userId, String layoutData) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String analysis = aiExternalService.analyzeLayout(layoutData, agent.getImageConfig());
        return createInteraction(agent, user, InteractionType.LAYOUT_ANALYSIS, layoutData, analysis);
    }

    @Override
    @Transactional
    public AgentInteractionDTO generateSubtitle(Long agentId, Long userId, String audioText) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String subtitle = aiExternalService.generateSubtitle(audioText, agent.getNlpConfig());
        return createInteraction(agent, user, InteractionType.SUBTITLE_GENERATION, audioText, subtitle);
    }

    @Override
    @Transactional
    public AgentInteractionDTO describeImage(Long agentId, Long userId, String imageData) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String description = aiExternalService.describeImage(imageData, agent.getImageConfig());
        return createInteraction(agent, user, InteractionType.IMAGE_DESCRIPTION, imageData, description);
    }

    @Override
    @Transactional
    public AgentInteractionDTO adaptLayout(Long agentId, Long userId, String layoutData) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String adaptedLayout = aiExternalService.adaptLayout(layoutData, agent.getImageConfig());
        return createInteraction(agent, user, InteractionType.LAYOUT_ADAPTATION, layoutData, adaptedLayout);
    }

    @Override
    @Transactional
    public AgentInteractionDTO automateTask(Long agentId, Long userId, String taskDescription) {
        AIAgent agent = getAgentEntity(agentId);
        User user = getUserEntity(userId);
        
        String result = aiExternalService.automateTask(taskDescription, agent.getNlpConfig());
        return createInteraction(agent, user, InteractionType.TASK_AUTOMATION, taskDescription, result);
    }

    @Override
    @Transactional
    public void updateAIModel(Long agentId, String modelType, String modelVersion) {
        AIAgent agent = getAgentEntity(agentId);
        // Update model configuration based on type
        switch (modelType.toLowerCase()) {
            case "nlp":
                agent.getNlpConfig().setLanguageModel(modelVersion);
                break;
            case "image":
                agent.getImageConfig().setObjectDetectionModel(modelVersion);
                break;
            case "voice":
                agent.getVoiceConfig().setSpeechRecognitionModel(modelVersion);
                break;
        }
        agentRepository.save(agent);
    }

    @Override
    @Transactional
    public void trainAIModel(Long agentId, String trainingData) {
        AIAgent agent = getAgentEntity(agentId);
        aiExternalService.trainModel(trainingData, agent.getNlpConfig());
    }

    // Helper methods
    private AIAgent getAgentEntity(Long id) {
        return agentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agent not found with id: " + id));
    }

    private User getUserEntity(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private AgentInteractionDTO createInteraction(AIAgent agent, User user, InteractionType type, 
                                                String action, String response) {
        AgentInteraction interaction = AgentInteraction.builder()
            .agent(agent)
            .user(user)
            .type(type)
            .action(action)
            .response(response)
            .timestamp(LocalDateTime.now())
            .successful(true)
            .build();
        
        interaction = interactionRepository.save(interaction);
        return mapToInteractionDTO(interaction);
    }

    // Mapping methods
    private AIAgentDTO mapToDTO(AIAgent agent) {
        return AIAgentDTO.builder()
            .id(agent.getId())
            .name(agent.getName())
            .version(agent.getVersion())
            .isActive(agent.isActive())
            .lastUpdate(agent.getLastUpdate())
            .nlpConfig(mapToNLPConfigDTO(agent.getNlpConfig()))
            .imageConfig(mapToImageConfigDTO(agent.getImageConfig()))
            .voiceConfig(mapToVoiceConfigDTO(agent.getVoiceConfig()))
            .activeUserIds(agent.getActiveUsers().stream()
                .map(User::getId)
                .collect(Collectors.toSet()))
            .build();
    }

    private AgentInteractionDTO mapToInteractionDTO(AgentInteraction interaction) {
        return AgentInteractionDTO.builder()
            .id(interaction.getId())
            .agentId(interaction.getAgent().getId())
            .userId(interaction.getUser().getId())
            .timestamp(interaction.getTimestamp())
            .type(interaction.getType())
            .action(interaction.getAction())
            .response(interaction.getResponse())
            .successful(interaction.isSuccessful())
            .build();
    }

    private AIAgent mapToEntity(AIAgentDTO dto) {
        return AIAgent.builder()
            .id(dto.getId())
            .name(dto.getName())
            .version(dto.getVersion())
            .isActive(dto.isActive())
            .lastUpdate(dto.getLastUpdate())
            .nlpConfig(mapToNLPConfig(dto.getNlpConfig()))
            .imageConfig(mapToImageConfig(dto.getImageConfig()))
            .voiceConfig(mapToVoiceConfig(dto.getVoiceConfig()))
            .build();
    }

    private void updateAgentFromDTO(AIAgent agent, AIAgentDTO dto) {
        agent.setName(dto.getName());
        agent.setVersion(dto.getVersion());
        agent.setActive(dto.isActive());
        agent.setLastUpdate(dto.getLastUpdate());
        agent.setNlpConfig(mapToNLPConfig(dto.getNlpConfig()));
        agent.setImageConfig(mapToImageConfig(dto.getImageConfig()));
        agent.setVoiceConfig(mapToVoiceConfig(dto.getVoiceConfig()));
    }

    private NLPConfigDTO mapToNLPConfigDTO(NLPConfig config) {
        return NLPConfigDTO.builder()
            .languageModel(config.getLanguageModel())
            .confidenceThreshold(config.getConfidenceThreshold())
            .enableContextAwareness(config.isEnableContextAwareness())
            .build();
    }

    private NLPConfig mapToNLPConfig(NLPConfigDTO dto) {
        return NLPConfig.builder()
            .languageModel(dto.getLanguageModel())
            .confidenceThreshold(dto.getConfidenceThreshold())
            .enableContextAwareness(dto.isEnableContextAwareness())
            .build();
    }

    private ImageProcessingConfigDTO mapToImageConfigDTO(ImageProcessingConfig config) {
        return ImageProcessingConfigDTO.builder()
            .objectDetectionModel(config.getObjectDetectionModel())
            .detectionThreshold(config.getDetectionThreshold())
            .enableOCR(config.isEnableOCR())
            .build();
    }

    private ImageProcessingConfig mapToImageConfig(ImageProcessingConfigDTO dto) {
        return ImageProcessingConfig.builder()
            .objectDetectionModel(dto.getObjectDetectionModel())
            .detectionThreshold(dto.getDetectionThreshold())
            .enableOCR(dto.isEnableOCR())
            .build();
    }

    private VoiceProcessingConfigDTO mapToVoiceConfigDTO(VoiceProcessingConfig config) {
        return VoiceProcessingConfigDTO.builder()
            .speechRecognitionModel(config.getSpeechRecognitionModel())
            .recognitionThreshold(config.getRecognitionThreshold())
            .enableNoiseReduction(config.isEnableNoiseReduction())
            .build();
    }

    private VoiceProcessingConfig mapToVoiceConfig(VoiceProcessingConfigDTO dto) {
        return VoiceProcessingConfig.builder()
            .speechRecognitionModel(dto.getSpeechRecognitionModel())
            .recognitionThreshold(dto.getRecognitionThreshold())
            .enableNoiseReduction(dto.isEnableNoiseReduction())
            .build();
    }

    // Additional mapping methods for configs...
} 