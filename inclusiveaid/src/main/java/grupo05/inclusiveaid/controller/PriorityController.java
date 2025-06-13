package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.service.PriorityService;
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

@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
@Tag(name = "Gerenciamento de Prioridades"
,description="APIs para gerenciamento de prioridades no sistema AID") @SecurityRequirement(name="bearerAuth")public class PriorityController {
    private final PriorityService priorityService;@Operation(summary="Listar prioridades")@ApiResponses(value={@ApiResponse(responseCode="200",description="Lista de prioridades", content = @Content(mediaType = "application/json", schema =@Schema(implementation=Page.class)))
})@GetMapping public ResponseEntity<Page<PriorityDTO>>

listAll(@RequestParam(defaultValue = "0") int page, 
@RequestParam(defaultValue = "10") int size) { 
return ResponseEntity.ok(priorityService.listAll(page, size)); 
}@Operation(summary="Obter prioridade por ID")@ApiResponses(value={@ApiResponse(responseCode="200",description="Prioridade encontrada", content = @Content(mediaType = "application/json", schema =@Schema(implementation=PriorityDTO.class))),@ApiResponse(responseCode="404",description="Prioridade não encontrada") 

})

    @GetMapping("/{id}")
    public ResponseEntity<PriorityDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(priorityService.getById(id));
    }@Operation(summary="Criar prioridade")@ApiResponses(value={@ApiResponse(responseCode="200",description="Prioridade criada", content = @Content(mediaType = "application/json", schema = @Schema(implementation=PriorityDTO.class)))

})

    @PostMapping
    public ResponseEntity<PriorityDTO> create(@Valid @RequestBody PriorityDTO dto) {
        return ResponseEntity.ok(priorityService.create(dto));
    }@Operation(summary="Atualizar prioridade")@ApiResponses(value={@ApiResponse(responseCode="200",description="Prioridade atualizada", content = @Content(mediaType = "application/json", schema =@Schema(implementation=PriorityDTO.class))),@ApiResponse(responseCode="404",description="Prioridade não encontrada") 

})

    @PutMapping("/{id}")
    public ResponseEntity<PriorityDTO> update(@PathVariable Long id,
            @Valid @RequestBody PriorityDTO dto) {
        return ResponseEntity.ok(priorityService.update(id, dto));
    }@Operation(summary="Excluir prioridade")@ApiResponses(value={@ApiResponse(responseCode="204",description="Prioridade excluída"), @ApiResponse(responseCode="404",description="Prioridade não encontrada") 

})

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        priorityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
