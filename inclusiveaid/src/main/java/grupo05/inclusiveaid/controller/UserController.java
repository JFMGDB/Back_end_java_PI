package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.aid.dto.UserDTO;
import grupo05.inclusiveaid.aid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService svc;

  @PostMapping
  public ResponseEntity<UserDTO> create(
      @RequestBody @Validated(UserDTO.Create.class) UserDTO dto) {
    return ResponseEntity.ok(svc.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(svc.getById(id));
  }

  @GetMapping
  public ResponseEntity<Page<UserDTO>> list(
      @RequestParam(defaultValue="0") int page,
      @RequestParam(defaultValue="10") int size) {
    return ResponseEntity.ok(svc.listAll(page,size));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update(
      @PathVariable Long id,
      @RequestBody @Validated(UserDTO.Update.class) UserDTO dto) {
    return ResponseEntity.ok(svc.update(id,dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    svc.delete(id);
    return ResponseEntity.noContent().build();
  }
}