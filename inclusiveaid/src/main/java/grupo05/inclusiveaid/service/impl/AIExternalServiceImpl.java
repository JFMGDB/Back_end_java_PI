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
/**
 * Serviço que encapsula chamadas a provedores externos de IA (ex.: OpenAI).
 * <p>
 * Responsável por delegar tarefas como geração de legendas, análise e adaptação
 * de layouts, processamento de comandos de voz e automação de tarefas.
 * Quando a integração está desativada via propriedade <code>openai.enabled</code>,
 * métodos retornam respostas mock para facilitar desenvolvimento offline.
 */
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
    /**
     * Processa um comando de voz utilizando modelo de linguagem.
     *
     * @param command texto já transcrito do comando de voz
     * @param config  configurações de NLP a serem utilizadas
     * @return resposta textual gerada pelo modelo ou valor mock se desativado
     */
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
    /**
     * Analisa a acessibilidade de um layout.
     *
     * @param layoutData representação do layout (ex.: JSON, HTML)
     * @param config      configurações de processamento de imagem
     * @return resultado da análise ou mock
     */
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
    /**
     * Gera legenda a partir de transcrição de áudio.
     *
     * @param audioText texto transcrito do áudio
     * @param config    configurações de NLP
     * @return legenda gerada ou mock
     */
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
    /**
     * Descreve uma imagem para fins de acessibilidade.
     *
     * @param imageData dados (base64 ou URL) da imagem
     * @param config    configurações de processamento de imagem
     * @return descrição gerada ou mock
     */
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
    /**
     * Adapta um layout para melhor acessibilidade.
     *
     * @param layoutData dados do layout a ser adaptado
     * @param config     configurações de imagem
     * @return layout adaptado ou mock
     */
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
    /**
     * Automatiza uma tarefa descrita textualmente.
     *
     * @param taskDescription descrição textual da tarefa
     * @param config          configurações de NLP
     * @return resposta de automação ou mock
     */
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
    /**
     * Treina (ou ajusta) um modelo de IA com dados específicos.
     * Atualmente não implementado — marcador para futura expansão.
     *
     * @param trainingData dados de treinamento
     * @param config       configurações de NLP
     */
    public void trainModel(String trainingData, NLPConfig config) {
        if (!openaiEnabled) {
            return;
        }
        // Implementa a lógica de treinamento do modelo aqui
        // Normalmente envolveria ajuste fino do modelo com os dados fornecidos
    }

    private String callOpenAI(Map<String, Object> request) {
        try {
            // Adiciona a API key nos headers
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

            // Extrai o texto da resposta no formato retornado pela OpenAI
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
            // Registra o erro e retorna uma resposta de fallback
            return "Error: " + e.getMessage();
        }
    }
} 