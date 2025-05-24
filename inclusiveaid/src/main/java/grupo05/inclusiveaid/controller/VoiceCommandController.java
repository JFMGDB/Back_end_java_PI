package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.service.VoiceCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para VoiceCommand.
 */
@RestController
@RequestMapping("/api/voice-commands")
@RequiredArgsConstructor
public class VoiceCommandController {
  private final VoiceCommandService svc;

  @PostMapping
  public ResponseEntity<VoiceCommandDTO> create(
    @Validated(VoiceCommandDTO.Create.class) @RequestBody VoiceCommandDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<VoiceCommandDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<VoiceCommandDTO>> list(
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