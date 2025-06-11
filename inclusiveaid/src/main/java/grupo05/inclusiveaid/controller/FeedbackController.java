package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.FeedbackDTO;
import grupo05.inclusiveaid.service.FeedbackService;
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
 * Controller for managing user feedback in the AID system.
 * Provides endpoints for creating, retrieving, listing, and deleting feedback from users about accessibility features.
 */
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "APIs for managing user feedback in the AID system")
public class FeedbackController {
    private final FeedbackService svc;

    @Operation(
        summary = "Create feedback",
        description = "Creates a new feedback entry from a user about accessibility features"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = FeedbackDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The feedback data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<FeedbackDTO> create(
        @Parameter(description = "Feedback data to create", required = true)
        @Validated(FeedbackDTO.Create.class) @RequestBody FeedbackDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Get feedback by ID",
        description = "Retrieves a specific feedback entry by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = FeedbackDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> get(
        @Parameter(description = "ID of the feedback to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "List all feedback",
        description = "Retrieves a paginated list of all feedback entries in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of feedback",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<FeedbackDTO>> list(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Delete feedback",
        description = "Deletes a feedback entry from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the feedback"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback not found"
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
        @Parameter(description = "ID of the feedback to delete", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}