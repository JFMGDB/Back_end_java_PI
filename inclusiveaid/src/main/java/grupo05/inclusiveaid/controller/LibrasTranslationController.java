package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import grupo05.inclusiveaid.service.LibrasTranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para gerenciar traduções em Libras.
 */
@RestController
@RequestMapping("/api/libras-translations")
@RequiredArgsConstructor
@Tag(name = "Libras Translation", description = "APIs for managing Libras translations")
@SecurityRequirement(name = "bearerAuth")
public class LibrasTranslationController {
    private final LibrasTranslationService service;

    @Operation(
        summary = "Create Libras translation",
        description = "Creates a new Libras translation in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created the translation",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The translation data is invalid"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PostMapping
    public ResponseEntity<LibrasTranslationDTO> create(
        @Parameter(description = "Translation data to create", required = true)
        @Valid @RequestBody LibrasTranslationDTO dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(
        summary = "Get Libras translation by ID",
        description = "Retrieves a specific Libras translation by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the translation",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Translation not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<LibrasTranslationDTO> getById(
        @Parameter(description = "ID of the translation to retrieve", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(
        summary = "List all Libras translations",
        description = "Retrieves a paginated list of all Libras translations in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of translations",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Page.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @GetMapping
    public ResponseEntity<Page<LibrasTranslationDTO>> listAll(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listAll(page, size));
    }

    @Operation(
        summary = "Update Libras translation",
        description = "Updates an existing Libras translation in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully updated the translation",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LibrasTranslationDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input - The translation data is invalid"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Translation not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<LibrasTranslationDTO> update(
        @Parameter(description = "ID of the translation to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated translation data", required = true)
        @Valid @RequestBody LibrasTranslationDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(
        summary = "Delete Libras translation",
        description = "Deletes a Libras translation from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted the translation"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Translation not found"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - User is not authenticated"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID of the translation to delete", required = true)
        @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 