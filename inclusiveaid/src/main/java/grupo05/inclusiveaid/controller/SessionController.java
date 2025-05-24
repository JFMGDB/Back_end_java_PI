package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SessionDTO;
import grupo05.inclusiveaid.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para Session.
 */
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
  private final SessionService svc;

  @PostMapping
  public ResponseEntity<SessionDTO> create(
    @Validated(SessionDTO.Create.class) @RequestBody SessionDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SessionDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<SessionDTO>> list(
    @RequestParam(defaultValue="0") int page,
    @RequestParam(defaultValue="10") int size) {
    return ResponseEntity.ok(svc.listAll(page,size));
  }

  @PutMapping("/{id}")
  public ResponseEntity<SessionDTO> update(
    @PathVariable Long id,
    @RequestBody SessionDTO dto) {
    return ResponseEntity.ok(svc.update(id,dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    svc.delete(id);
    return ResponseEntity.noContent().build();
  }
}