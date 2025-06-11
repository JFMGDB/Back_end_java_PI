package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import grupo05.inclusiveaid.service.SuggestionService;
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
 * Controller for managing accessibility suggestions in the AID system.
 * Provides endpoints for creating, retrieving, listing, and deleting suggestions for improving digital accessibility.
 */
@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
@Tag(name = "Suggestion", description = "APIs for managing accessibility suggestions in the AID system")
public class SuggestionController {
    private final SuggestionService svc;

    @Operation(
        summary = "Create suggestion",
        description = "Creates a new suggestion for improving accessibility features"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the suggestion",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The suggestion data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<SuggestionDTO> create(
        @Parameter(description = "Suggestion data to create", required = true)
        @Validated(SuggestionDTO.Create.class) @RequestBody SuggestionDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Get suggestion by ID",
        description = "Retrieves a specific suggestion by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the suggestion",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Suggestion not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuggestionDTO> get(
        @Parameter(description = "ID of the suggestion to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "List all suggestions",
        description = "Retrieves a paginated list of all suggestions in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of suggestions",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<SuggestionDTO>> list(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Update suggestion",
        description = "Updates an existing suggestion with new data"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the suggestion",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SuggestionDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The suggestion data is invalid"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Suggestion not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SuggestionDTO> update(
        @Parameter(description = "ID of the suggestion to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated suggestion data", required = true)
        @Validated(SuggestionDTO.Update.class) @RequestBody SuggestionDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @Operation(
        summary = "Delete suggestion",
        description = "Deletes a suggestion from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the suggestion"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Suggestion not found"
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
        @Parameter(description = "ID of the suggestion to delete", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}