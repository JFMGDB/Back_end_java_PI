package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.service.VoiceCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing voice commands in the AID system.
 * Provides endpoints for creating, retrieving, listing, and deleting voice commands.
 */
@RestController
@RequestMapping("/api/voice-commands")
@RequiredArgsConstructor
@Tag(name = "Voice Command", description = "APIs for managing voice commands in the AID system")
public class VoiceCommandController {
    private final VoiceCommandService svc;

    @Operation(
        summary = "Create voice command",
        description = "Creates a new voice command record in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the voice command",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The voice command data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<VoiceCommandDTO> create(
        @Parameter(description = "Voice command data to create", required = true)
        @Validated(VoiceCommandDTO.Create.class) @RequestBody VoiceCommandDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Get voice command by ID",
        description = "Retrieves a specific voice command by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the voice command",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Voice command not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<VoiceCommandDTO> get(
        @Parameter(description = "ID of the voice command to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "List all voice commands",
        description = "Retrieves a paginated list of all voice commands in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of voice commands",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<VoiceCommandDTO>> list(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Delete voice command",
        description = "Deletes a voice command from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the voice command"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Voice command not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID of the voice command to delete", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Update voice command",
        description = "Updates an existing voice command in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the voice command",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VoiceCommandDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The voice command data is invalid"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Voice command not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden - User does not have required permissions"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<VoiceCommandDTO> update(
        @Parameter(description = "ID of the voice command to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated voice command data", required = true)
        @Validated(VoiceCommandDTO.Update.class) @RequestBody VoiceCommandDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }
}