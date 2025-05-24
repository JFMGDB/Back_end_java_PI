package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.DisabilityTypeDTO;
import grupo05.inclusiveaid.service.DisabilityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para DisabilityType.
 */
@RestController
@RequestMapping("/api/disability-types")
@RequiredArgsConstructor
public class DisabilityTypeController {
  private final DisabilityTypeService svc;

  @PostMapping
  public ResponseEntity<DisabilityTypeDTO> create(
    @Validated(DisabilityTypeDTO.Create.class) @RequestBody DisabilityTypeDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DisabilityTypeDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<DisabilityTypeDTO>> list(
    @RequestParam(defaultValue="0") int page,
    @RequestParam(defaultValue="10") int size) {
    return ResponseEntity.ok(svc.listAll(page,size));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DisabilityTypeDTO> update(
    @PathVariable Long id,
    @Validated(DisabilityTypeDTO.Update.class) @RequestBody DisabilityTypeDTO dto) {
    return ResponseEntity.ok(svc.update(id,dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    svc.delete(id);
    return ResponseEntity.noContent().build();
  }
}