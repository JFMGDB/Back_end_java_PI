package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.service.LayoutAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para LayoutAnalysis.
 */
@RestController
@RequestMapping("/api/layout-analyses")
@RequiredArgsConstructor
public class LayoutAnalysisController {
  private final LayoutAnalysisService svc;

  @PostMapping
  public ResponseEntity<LayoutAnalysisDTO> create(
    @Validated(LayoutAnalysisDTO.Create.class) @RequestBody LayoutAnalysisDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<LayoutAnalysisDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<LayoutAnalysisDTO>> list(
    @RequestParam(defaultValue="0") int page,
    @RequestParam(defaultValue="10") int size) {
    return ResponseEntity.ok(svc.listAll(page,size));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    svc.delete(id);
    return ResponseEntity.noContent().build();
  }
}
