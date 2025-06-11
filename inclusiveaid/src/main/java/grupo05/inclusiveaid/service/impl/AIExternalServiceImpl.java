package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.service.AIExternalService;
import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIExternalServiceImpl implements AIExternalService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.enabled:false}")
    private boolean openaiEnabled;

    @Override
    public String processVoiceCommand(String command, NLPConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for: " + command;
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getLanguageModel());
        request.put("prompt", "Process voice command: " + command);
        request.put("temperature", 0.7);
        
        return callOpenAI(request);
    }

    @Override
    public String analyzeLayout(String layoutData, ImageProcessingConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for layout analysis.";
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getObjectDetectionModel());
        request.put("prompt", "Analyze layout: " + layoutData);
        request.put("temperature", 0.5);
        
        return callOpenAI(request);
    }

    @Override
    public String generateSubtitle(String audioText, NLPConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for subtitle generation.";
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getLanguageModel());
        request.put("prompt", "Generate subtitle for: " + audioText);
        request.put("temperature", 0.3);
        
        return callOpenAI(request);
    }

    @Override
    public String describeImage(String imageData, ImageProcessingConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for image description.";
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getObjectDetectionModel());
        request.put("prompt", "Describe image: " + imageData);
        request.put("temperature", 0.5);
        
        return callOpenAI(request);
    }

    @Override
    public String adaptLayout(String layoutData, ImageProcessingConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for layout adaptation.";
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getObjectDetectionModel());
        request.put("prompt", "Adapt layout for accessibility: " + layoutData);
        request.put("temperature", 0.4);
        
        return callOpenAI(request);
    }

    @Override
    public String automateTask(String taskDescription, NLPConfig config) {
        if (!openaiEnabled) {
            return "OpenAI integration is disabled. This is a mock response for task automation.";
        }
        Map<String, Object> request = new HashMap<>();
        request.put("model", config.getLanguageModel());
        request.put("prompt", "Automate task: " + taskDescription);
        request.put("temperature", 0.6);
        
        return callOpenAI(request);
    }

    @Override
    public void trainModel(String trainingData, NLPConfig config) {
        if (!openaiEnabled) {
            return;
        }
        // Implement model training logic here
        // This would typically involve fine-tuning the model with the provided data
    }

    private String callOpenAI(Map<String, Object> request) {
        try {
            // Add API key to headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
            
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            // Extract the response text from OpenAI's response format
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = objectMapper.convertValue(
                    responseBody.get("choices"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)
                );
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    return (String) choice.get("text");
                }
            }
            
            return "Error processing AI request";
        } catch (Exception e) {
            // Log the error and return a fallback response
            return "Error: " + e.getMessage();
        }
    }
} 