package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;

public interface AIExternalService {
    String processVoiceCommand(String command, NLPConfig config);
    String analyzeLayout(String layoutData, ImageProcessingConfig config);
    String generateSubtitle(String audioText, NLPConfig config);
    String describeImage(String imageData, ImageProcessingConfig config);
    String adaptLayout(String layoutData, ImageProcessingConfig config);
    String automateTask(String taskDescription, NLPConfig config);
    void trainModel(String trainingData, NLPConfig config);
} 