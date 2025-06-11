package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.AIAgentDTO;
import grupo05.inclusiveaid.dto.NLPConfigDTO;
import grupo05.inclusiveaid.dto.ImageProcessingConfigDTO;
import grupo05.inclusiveaid.dto.VoiceProcessingConfigDTO;
import grupo05.inclusiveaid.entity.AIAgent;
import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;
import grupo05.inclusiveaid.config.VoiceProcessingConfig;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class AIAgentMapper {
    
    public AIAgentDTO toDTO(AIAgent agent) {
        if (agent == null) {
            return null;
        }
        
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
                        .map(user -> user.getId())
                        .collect(Collectors.toSet()))
                .disabilityConfigIds(agent.getDisabilityConfigs().stream()
                        .map(config -> config.getId())
                        .collect(Collectors.toSet()))
                .build();
    }
    
    public AIAgent toEntity(AIAgentDTO dto) {
        if (dto == null) {
            return null;
        }
        
        AIAgent agent = new AIAgent();
        agent.setId(dto.getId());
        agent.setName(dto.getName());
        agent.setVersion(dto.getVersion());
        agent.setActive(dto.isActive());
        agent.setLastUpdate(dto.getLastUpdate());
        agent.setNlpConfig(mapToNLPConfig(dto.getNlpConfig()));
        agent.setImageConfig(mapToImageConfig(dto.getImageConfig()));
        agent.setVoiceConfig(mapToVoiceConfig(dto.getVoiceConfig()));
        return agent;
    }
    
    private NLPConfigDTO mapToNLPConfigDTO(NLPConfig config) {
        if (config == null) return null;
        return NLPConfigDTO.builder()
                .languageModel(config.getLanguageModel())
                .confidenceThreshold(config.getConfidenceThreshold())
                .enableContextAwareness(config.isEnableContextAwareness())
                .build();
    }
    
    private ImageProcessingConfigDTO mapToImageConfigDTO(ImageProcessingConfig config) {
        if (config == null) return null;
        return ImageProcessingConfigDTO.builder()
                .objectDetectionModel(config.getObjectDetectionModel())
                .detectionThreshold(config.getDetectionThreshold())
                .enableOCR(config.isEnableOCR())
                .build();
    }
    
    private VoiceProcessingConfigDTO mapToVoiceConfigDTO(VoiceProcessingConfig config) {
        if (config == null) return null;
        return VoiceProcessingConfigDTO.builder()
                .speechRecognitionModel(config.getSpeechRecognitionModel())
                .recognitionThreshold(config.getRecognitionThreshold())
                .enableNoiseReduction(config.isEnableNoiseReduction())
                .build();
    }
    
    private NLPConfig mapToNLPConfig(NLPConfigDTO dto) {
        if (dto == null) return null;
        NLPConfig config = new NLPConfig();
        config.setLanguageModel(dto.getLanguageModel());
        config.setConfidenceThreshold(dto.getConfidenceThreshold());
        config.setEnableContextAwareness(dto.isEnableContextAwareness());
        return config;
    }
    
    private ImageProcessingConfig mapToImageConfig(ImageProcessingConfigDTO dto) {
        if (dto == null) return null;
        ImageProcessingConfig config = new ImageProcessingConfig();
        config.setObjectDetectionModel(dto.getObjectDetectionModel());
        config.setDetectionThreshold(dto.getDetectionThreshold());
        config.setEnableOCR(dto.isEnableOCR());
        return config;
    }
    
    private VoiceProcessingConfig mapToVoiceConfig(VoiceProcessingConfigDTO dto) {
        if (dto == null) return null;
        VoiceProcessingConfig config = new VoiceProcessingConfig();
        config.setSpeechRecognitionModel(dto.getSpeechRecognitionModel());
        config.setRecognitionThreshold(dto.getRecognitionThreshold());
        config.setEnableNoiseReduction(dto.isEnableNoiseReduction());
        return config;
    }
} 