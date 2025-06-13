package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.CategoryDTO;
import grupo05.inclusiveaid.service.CategoryService;
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
* Controlador REST para Categoria. 
*/
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Gerenciamento de Categorias"
,description="APIs para gerenciamento de categorias no sistema AID") @SecurityRequirement(name="bearerAuth")public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "Listar categorias"
    ,description="Lista todas as
    categorias com paginação") @ApiResponses(value={@ApiResponse(responseCode="200",description="Lista de categorias", content = @Content(mediaType = "application/json", schema =@Schema(implementation=Page.class)))
})@GetMapping public ResponseEntity<Page<CategoryDTO>>

listAll(@RequestParam(defaultValue = "0") int page, 
@RequestParam(defaultValue = "10") int size) { 
return ResponseEntity.ok(categoryService.listAll(page, size)); 
}@Operation(summary="Obter categoria por ID")@ApiResponses(value={@ApiResponse(responseCode="200",description="Categoria encontrada", content = @Content(mediaType = "application/json", schema =@Schema(implementation=CategoryDTO.class))),@ApiResponse(responseCode="404",description="Categoria não encontrada") 

})

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }@Operation(summary="Criar categoria")@ApiResponses(value={@ApiResponse(responseCode="200",description="Categoria criada", content = @Content(mediaType = "application/json", schema = @Schema(implementation=CategoryDTO.class)))

})

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }@Operation(summary="Atualizar categoria")@ApiResponses(value={@ApiResponse(responseCode="200",description="Categoria atualizada", content = @Content(mediaType = "application/json", schema =@Schema(implementation=CategoryDTO.class))),@ApiResponse(responseCode="404",description="Categoria não encontrada") 

})

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,
            @Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }@Operation(summary="Excluir categoria")@ApiResponses(value={@ApiResponse(responseCode="204",description="Categoria excluída"), @ApiResponse(responseCode="404",description="Categoria não encontrada") 

})

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}