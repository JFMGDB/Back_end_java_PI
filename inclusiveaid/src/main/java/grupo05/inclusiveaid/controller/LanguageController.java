package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.service.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
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
* Controlador REST para Idiomas. 
*/
@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
@Tag(name = "Gerenciamento de Idiomas"
,description="APIs para gerenciamento de idiomas no sistema AID") @SecurityRequirement(name="bearerAuth")public class LanguageController {
    private final LanguageService languageService;
    @Operation(summary = "Listar idiomas"
    ,description="Lista todos os idiomas com paginação") @ApiResponses(value={@ApiResponse(responseCode="200",description="Lista de idiomas", content = @Content(mediaType = "application/json", schema = @Schema(implementation=Page.class)))
})@GetMapping public ResponseEntity<Page<LanguageDTO>>

listAll(@RequestParam(defaultValue = "0") int page, 
@RequestParam(defaultValue = "10") int size) { 
return ResponseEntity.ok(languageService.listAll(page, size)); 
}@Operation(summary="Obter idioma por ID")@ApiResponses(value={@ApiResponse(responseCode="200",description="Idioma encontrado", content = @Content(mediaType = "application/json", schema =@Schema(implementation=LanguageDTO.class))),@ApiResponse(responseCode="404",description="Idioma não encontrado") 

})

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(languageService.getById(id));
    }@Operation(summary="Criar idioma")@ApiResponses(value={@ApiResponse(responseCode="200",description="Idioma criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation=LanguageDTO.class)))

})

    @PostMapping
    public ResponseEntity<LanguageDTO> create(@Valid @RequestBody LanguageDTO dto) {
        return ResponseEntity.ok(languageService.create(dto));
    }@Operation(summary="Atualizar idioma")@ApiResponses(value={@ApiResponse(responseCode="200",description="Idioma atualizado", content = @Content(mediaType = "application/json", schema =@Schema(implementation=LanguageDTO.class))),@ApiResponse(responseCode="404",description="Idioma não encontrado") 

})

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO> update(@PathVariable Long id,
            @Valid @RequestBody LanguageDTO dto) {
        return ResponseEntity.ok(languageService.update(id, dto));
    }@Operation(summary="Excluir idioma")@ApiResponses(value={@ApiResponse(responseCode="204",description="Idioma excluído"), @ApiResponse(responseCode="404",description="Idioma não encontrado") 

})

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}