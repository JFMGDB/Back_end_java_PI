package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.service.SubtitleService;
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
 * Controller for managing accessibility subtitles in the AID system.
 * Provides endpoints for creating, retrieving, listing, and deleting subtitles for hearing-impaired users.
 */
@RestController
@RequestMapping("/api/subtitles")
@RequiredArgsConstructor
@Tag(name = "Subtitle", description = "APIs for managing accessibility subtitles in the AID system")
public class SubtitleController {
    private final SubtitleService svc;

    @Operation(
        summary = "Create subtitle",
        description = "Creates a new subtitle record for accessibility purposes"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the subtitle",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The subtitle data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<SubtitleDTO> create(
        @Parameter(description = "Subtitle data to create", required = true)
        @Validated(SubtitleDTO.Create.class) @RequestBody SubtitleDTO dto
    ) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @Operation(
        summary = "Get subtitle by ID",
        description = "Retrieves a specific subtitle by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the subtitle",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Subtitle not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubtitleDTO> get(
        @Parameter(description = "ID of the subtitle to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @Operation(
        summary = "List all subtitles",
        description = "Retrieves a paginated list of all subtitles in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of subtitles",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<SubtitleDTO>> list(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(svc.listAll(page, size));
    }

    @Operation(
        summary = "Update subtitle",
        description = "Updates an existing subtitle with new text"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the subtitle",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SubtitleDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The subtitle data is invalid"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Subtitle not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SubtitleDTO> update(
        @Parameter(description = "ID of the subtitle to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated subtitle data", required = true)
        @Validated(SubtitleDTO.Update.class) @RequestBody SubtitleDTO dto
    ) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @Operation(
        summary = "Delete subtitle",
        description = "Deletes a subtitle from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the subtitle"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Subtitle not found"
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
        @Parameter(description = "ID of the subtitle to delete", required = true)
        @PathVariable Long id
    ) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
