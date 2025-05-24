package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.service.AdaptationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para Adaptation.
 */
@RestController
@RequestMapping("/api/adaptations")
@RequiredArgsConstructor
public class AdaptationController {
  private final AdaptationService svc;

  @PostMapping
  public ResponseEntity<AdaptationDTO> create(
    @Validated(AdaptationDTO.Create.class) @RequestBody AdaptationDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AdaptationDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<AdaptationDTO>> list(
    @RequestParam(defaultValue="0") int page,
    @RequestParam(defaultValue="10") int size) {
    return ResponseEntity.ok(svc.listAll(page,size));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AdaptationDTO> update(
    @PathVariable Long id,
    @Validated(AdaptationDTO.Update.class) @RequestBody AdaptationDTO dto) {
    return ResponseEntity.ok(svc.update(id,dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    svc.delete(id);
    return ResponseEntity.noContent().build();
  }
}