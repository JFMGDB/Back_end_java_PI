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

@RestController
@RequestMapping("/api/ai-agents")
@RequiredArgsConstructor
@Tag(name = "AI Agent", description = "APIs for managing AI agents and their interactions in the AID system")
public class AIAgentController {

    private final AIAgentService aiAgentService;

    @Operation(
        summary = "Create a new AI agent",
        description = "Creates a new AI agent with specified configuration and capabilities"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the AI agent",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The agent data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<AIAgentDTO> createAgent(
        @Parameter(description = "AI agent configuration data", required = true)
        @Valid @RequestBody AIAgentDTO agentDTO
    ) {
        return ResponseEntity.ok(aiAgentService.createAgent(agentDTO));
    }

    @Operation(
        summary = "Update an existing AI agent",
        description = "Updates the configuration and capabilities of an existing AI agent"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the AI agent",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The agent data is invalid"
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

    @Operation(
        summary = "Delete an AI agent",
        description = "Removes an AI agent from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the AI agent"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent not found"
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

    @Operation(
        summary = "Get an AI agent by ID",
        description = "Retrieves detailed information about a specific AI agent"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the AI agent",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AIAgentDTO> getAgent(
        @Parameter(description = "ID of the AI agent to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(aiAgentService.getAgent(id));
    }

    @Operation(
        summary = "Get all AI agents",
        description = "Retrieves a list of all AI agents in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all AI agents",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        )
    })
    @GetMapping
    public ResponseEntity<List<AIAgentDTO>> getAllAgents() {
        return ResponseEntity.ok(aiAgentService.getAllAgents());
    }

    @Operation(
        summary = "Get all active AI agents",
        description = "Retrieves a list of all currently active AI agents"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved active AI agents",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AIAgentDTO.class))
        )
    })
    @GetMapping("/active")
    public ResponseEntity<List<AIAgentDTO>> getActiveAgents() {
        return ResponseEntity.ok(aiAgentService.getActiveAgents());
    }

    @Operation(
        summary = "Process a voice command",
        description = "Processes a voice command for a specific user using the specified AI agent"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully processed the voice command",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Analyze a layout",
        description = "Analyzes a UI layout for accessibility improvements"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully analyzed the layout",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Generate subtitles",
        description = "Generates subtitles for audio content"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully generated subtitles",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Describe an image",
        description = "Generates a description of an image for visually impaired users"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully described the image",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Adapt a layout for accessibility",
        description = "Modifies a UI layout to improve accessibility for users with disabilities"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully adapted the layout",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Automate a task",
        description = "Automates a task using AI capabilities"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully automated the task",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgentInteractionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent or user not found"
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

    @Operation(
        summary = "Update AI model",
        description = "Updates the AI model configuration for an agent"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the AI model"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent not found"
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

    @Operation(
        summary = "Train AI model",
        description = "Initiates training of the AI model with provided data"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully initiated model training"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "AI agent not found"
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