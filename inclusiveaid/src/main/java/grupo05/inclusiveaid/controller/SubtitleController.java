package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.service.SubtitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para Subtitle.
 */
@RestController
@RequestMapping("/api/subtitles")
@RequiredArgsConstructor
public class SubtitleController {
  private final SubtitleService svc;

  @PostMapping
  public ResponseEntity<SubtitleDTO> create(
    @Validated(SubtitleDTO.Create.class) @RequestBody SubtitleDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubtitleDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<SubtitleDTO>> list(
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
