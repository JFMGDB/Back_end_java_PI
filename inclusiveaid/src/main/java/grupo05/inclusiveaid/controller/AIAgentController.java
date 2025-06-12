package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AIAgentDTO;
import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import grupo05.inclusiveaid.service.AIAgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelo gerenciamento de agentes de IA no sistema InclusiveAID.
 * Fornece endpoints para criar, atualizar, excluir e consultar agentes de IA, além de
 * gerenciar suas interações com usuários e funcionalidades específicas.
 * 
 * Este controlador implementa operações para:
 * - Gerenciamento básico de agentes (CRUD)
 * - Processamento de comandos de voz
 * - Análise e adaptação de layouts
 * - Geração de legendas
 * - Descrição de imagens
 * - Automação de tarefas
 * - Gerenciamento de modelos de IA
 * 
 * Os agentes de IA são componentes fundamentais do sistema, responsáveis por:
 * - Processar e interpretar comandos de voz dos usuários
 * - Analisar e adaptar interfaces para melhorar a acessibilidade
 * - Gerar legendas e descrições para conteúdo multimídia
 * - Automatizar tarefas comuns para facilitar o uso do sistema
 * - Aprender e se adaptar com base nas interações dos usuários
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/ai-agents")
@RequiredArgsConstructor
@Tag(name = "Agente de IA", description = "APIs para gerenciamento de agentes de IA e suas interações no sistema AID")
public class AIAgentController {

    private final AIAgentService aiAgentService;

    /**
     * Cria um novo agente de IA no sistema.
     * 
     * Este endpoint permite a criação de um novo agente com configurações e capacidades específicas.
     * O agente criado pode ser utilizado para diversas funcionalidades do sistema.
     * 
     * @param agentDTO Dados de configuração do agente de IA
     * @return ResponseEntity contendo os dados do agente criado
     * @throws ValidationException se os dados do agente forem inválidos
     * @throws UnauthorizedException se o usuário não estiver autenticado
     */
    @Operation(
        summary = "Criar um novo agente de IA",
        description = "Cria um novo agente de IA com configuração e capacidades específicas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Agente de IA criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do agente são inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Não autorizado - Usuário não está autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<AIAgentDTO> createAgent(
        @Parameter(description = "AI agent configuration data", required = true)
        @Valid @RequestBody AIAgentDTO agentDTO
    ) {
        return ResponseEntity.ok(aiAgentService.createAgent(agentDTO));
    }

    /**
     * Atualiza um agente de IA existente.
     * 
     * Este endpoint permite a atualização das configurações e capacidades de um agente existente.
     * Todas as alterações são validadas antes de serem aplicadas.
     * 
     * @param id ID do agente a ser atualizado
     * @param agentDTO Novos dados de configuração do agente
     * @return ResponseEntity contendo os dados atualizados do agente
     * @throws ResourceNotFoundException se o agente não for encontrado
     * @throws ValidationException se os dados de atualização forem inválidos
     */
    @Operation(
        summary = "Atualizar um agente de IA existente",
        description = "Atualiza a configuração e capacidades de um agente de IA existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Agente de IA atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA não encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida - Os dados do agente são inválidos"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AIAgentDTO> updateAgent(
        @Parameter(description = "ID of the AI agent to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated AI agent configuration", required = true)
        @Valid @RequestBody AIAgentDTO agentDTO
    ) {
        return ResponseEntity.ok(aiAgentService.updateAgent(id, agentDTO));
    }

    /**
     * Remove um agente de IA do sistema.
     * 
     * Este endpoint permite a exclusão permanente de um agente de IA.
     * Todas as interações e dados associados ao agente também são removidos.
     * 
     * @param id ID do agente a ser excluído
     * @return ResponseEntity vazio com status 204 (No Content)
     * @throws ResourceNotFoundException se o agente não for encontrado
     */
    @Operation(
        summary = "Excluir um agente de IA",
        description = "Remove um agente de IA do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Agente de IA excluído com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA não encontrado"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(
        @Parameter(description = "ID of the AI agent to delete", required = true)
        @PathVariable Long id
    ) {
        aiAgentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recupera informações detalhadas de um agente de IA específico.
     * 
     * Este endpoint retorna todos os dados de configuração e status de um agente específico.
     * 
     * @param id ID do agente a ser recuperado
     * @return ResponseEntity contendo os dados do agente
     * @throws ResourceNotFoundException se o agente não for encontrado
     */
    @Operation(
        summary = "Obter um agente de IA por ID",
        description = "Recupera informações detalhadas sobre um agente de IA específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Agente de IA recuperado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA não encontrado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AIAgentDTO> getAgent(
        @Parameter(description = "ID of the AI agent to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(aiAgentService.getAgent(id));
    }

    /**
     * Recupera a lista de todos os agentes de IA no sistema.
     * 
     * Este endpoint retorna informações básicas de todos os agentes cadastrados,
     * independente de seu status atual.
     * 
     * @return ResponseEntity contendo a lista de agentes
     */
    @Operation(
        summary = "Obter todos os agentes de IA",
        description = "Recupera uma lista de todos os agentes de IA no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Agentes de IA recuperados com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        )
    })
    @GetMapping
    public ResponseEntity<List<AIAgentDTO>> getAllAgents() {
        return ResponseEntity.ok(aiAgentService.getAllAgents());
    }

    /**
     * Recupera a lista de agentes de IA ativos no sistema.
     * 
     * Este endpoint retorna apenas os agentes que estão atualmente ativos e
     * disponíveis para interação com os usuários.
     * 
     * @return ResponseEntity contendo a lista de agentes ativos
     */
    @Operation(
        summary = "Obter todos os agentes de IA ativos",
        description = "Recupera uma lista de todos os agentes de IA atualmente ativos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Agentes de IA ativos recuperados com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        )
    })
    @GetMapping("/active")
    public ResponseEntity<List<AIAgentDTO>> getActiveAgents() {
        return ResponseEntity.ok(aiAgentService.getActiveAgents());
    }

    /**
     * Processa um comando de voz para um usuário específico.
     * 
     * Este endpoint permite que um agente de IA processe e execute um comando
     * de voz fornecido por um usuário, retornando o resultado da interação.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param command Comando de voz a ser processado
     * @return ResponseEntity contendo o resultado da interação
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Processar um comando de voz",
        description = "Processa um comando de voz para um usuário específico usando o agente de IA especificado"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Comando de voz processado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/voice-command")
    public ResponseEntity<AgentInteractionDTO> processVoiceCommand(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Voice command to process", required = true)
        @RequestBody String command
    ) {
        return ResponseEntity.ok(aiAgentService.processVoiceCommand(agentId, userId, command));
    }

    /**
     * Analisa um layout de interface para melhorias de acessibilidade.
     * 
     * Este endpoint permite que um agente de IA analise um layout de interface
     * e sugira melhorias para torná-lo mais acessível.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param layoutData Dados do layout a ser analisado
     * @return ResponseEntity contendo as sugestões de melhorias
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Analisar um layout",
        description = "Analisa um layout de interface para melhorias de acessibilidade"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Layout analisado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/analyze-layout")
    public ResponseEntity<AgentInteractionDTO> analyzeLayout(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Layout data to analyze", required = true)
        @RequestBody String layoutData
    ) {
        return ResponseEntity.ok(aiAgentService.analyzeLayout(agentId, userId, layoutData));
    }

    /**
     * Gera legendas para conteúdo de áudio.
     * 
     * Este endpoint permite que um agente de IA gere legendas para
     * conteúdo de áudio, melhorando a acessibilidade para usuários
     * com deficiência auditiva.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param audioText Texto do áudio para gerar legendas
     * @return ResponseEntity contendo as legendas geradas
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Gerar legendas",
        description = "Gera legendas para conteúdo de áudio"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Legendas geradas com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/generate-subtitle")
    public ResponseEntity<AgentInteractionDTO> generateSubtitle(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Audio text to generate subtitles for", required = true)
        @RequestBody String audioText
    ) {
        return ResponseEntity.ok(aiAgentService.generateSubtitle(agentId, userId, audioText));
    }

    /**
     * Gera uma descrição de uma imagem para usuários com deficiência visual.
     * 
     * Este endpoint permite que um agente de IA analise uma imagem e
     * gere uma descrição detalhada para usuários com deficiência visual.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param imageData Dados da imagem a ser descrita
     * @return ResponseEntity contendo a descrição gerada
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Descrever uma imagem",
        description = "Gera uma descrição de uma imagem para usuários com deficiência visual"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Imagem descrita com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/describe-image")
    public ResponseEntity<AgentInteractionDTO> describeImage(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Image data to describe", required = true)
        @RequestBody String imageData
    ) {
        return ResponseEntity.ok(aiAgentService.describeImage(agentId, userId, imageData));
    }

    /**
     * Adapta um layout para melhorar a acessibilidade.
     * 
     * Este endpoint permite que um agente de IA modifique um layout
     * para torná-lo mais acessível para usuários com deficiência.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param layoutData Dados do layout a ser adaptado
     * @return ResponseEntity contendo o layout adaptado
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Adaptar um layout para acessibilidade",
        description = "Modifica um layout de interface para melhorar a acessibilidade para usuários com deficiência"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Layout adaptado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/adapt-layout")
    public ResponseEntity<AgentInteractionDTO> adaptLayout(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Layout data to adapt", required = true)
        @RequestBody String layoutData
    ) {
        return ResponseEntity.ok(aiAgentService.adaptLayout(agentId, userId, layoutData));
    }

    /**
     * Automatiza uma tarefa usando capacidades de IA.
     * 
     * Este endpoint permite que um agente de IA automatize uma tarefa
     * específica com base em uma descrição fornecida.
     * 
     * @param agentId ID do agente de IA
     * @param userId ID do usuário
     * @param taskDescription Descrição da tarefa a ser automatizada
     * @return ResponseEntity contendo o resultado da automação
     * @throws ResourceNotFoundException se o agente ou usuário não for encontrado
     */
    @Operation(
        summary = "Automatizar uma tarefa",
        description = "Automatiza uma tarefa usando capacidades de IA"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa automatizada com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA ou usuário não encontrado"
        )
    })
    @PostMapping("/{agentId}/users/{userId}/automate-task")
    public ResponseEntity<AgentInteractionDTO> automateTask(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "ID of the user", required = true)
        @PathVariable Long userId,
        @Parameter(description = "Task description to automate", required = true)
        @RequestBody String taskDescription
    ) {
        return ResponseEntity.ok(aiAgentService.automateTask(agentId, userId, taskDescription));
    }

    /**
     * Atualiza a configuração do modelo de IA de um agente.
     * 
     * Este endpoint permite atualizar o tipo e versão do modelo de IA
     * utilizado por um agente específico.
     * 
     * @param agentId ID do agente de IA
     * @param modelType Tipo do modelo de IA
     * @param modelVersion Versão do modelo de IA
     * @return ResponseEntity vazio com status 200 (OK)
     * @throws ResourceNotFoundException se o agente não for encontrado
     */
    @Operation(
        summary = "Atualizar modelo de IA",
        description = "Atualiza a configuração do modelo de IA para um agente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Modelo de IA atualizado com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA não encontrado"
        )
    })
    @PutMapping("/{agentId}/model")
    public ResponseEntity<Void> updateAIModel(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "Type of the AI model", required = true)
        @RequestParam String modelType,
        @Parameter(description = "Version of the AI model", required = true)
        @RequestParam String modelVersion
    ) {
        aiAgentService.updateAIModel(agentId, modelType, modelVersion);
        return ResponseEntity.ok().build();
    }

    /**
     * Inicia o treinamento do modelo de IA com dados fornecidos.
     * 
     * Este endpoint permite iniciar o processo de treinamento do modelo de IA
     * de um agente específico usando dados fornecidos.
     * 
     * @param agentId ID do agente de IA
     * @param trainingData Dados para treinamento do modelo
     * @return ResponseEntity vazio com status 200 (OK)
     * @throws ResourceNotFoundException se o agente não for encontrado
     */
    @Operation(
        summary = "Treinar modelo de IA",
        description = "Inicia o treinamento do modelo de IA com os dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Treinamento do modelo iniciado com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Agente de IA não encontrado"
        )
    })
    @PostMapping("/{agentId}/train")
    public ResponseEntity<Void> trainAIModel(
        @Parameter(description = "ID of the AI agent", required = true)
        @PathVariable Long agentId,
        @Parameter(description = "Training data for the model", required = true)
        @RequestBody String trainingData
    ) {
        aiAgentService.trainAIModel(agentId, trainingData);
        return ResponseEntity.ok().build();
    }
} 