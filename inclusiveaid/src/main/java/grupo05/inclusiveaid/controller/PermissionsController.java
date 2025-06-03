package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.entity.Permissions;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import grupo05.inclusiveaid.service.PermissionsService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionsController {
    private final PermissionsService permissionsService;
    
    @GetMapping
    public ResponseEntity<List<Permissions>> getAllPermissions() {
        List<Permissions> permissions = permissionsService.getAllPermissions();
        if (permissions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            
        }
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissions> getPermissionById(@PathVariable Long id){
        return ResponseEntity.ok(permissionsService.getPermissionsById(id));
    }

    @PostMapping
    public ResponseEntity<Permissions> postPermission(@RequestBody Permissions permissions) {
        
        return ResponseEntity.ok(permissionsService.creatPermissions(permissions));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermissionById(@PathVariable Long id){
        permissionsService.deletePermissionsById(id);
        return ResponseEntity.ok("Permissão deletada.");
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permissions> updatePermission(@PathVariable Long id, @RequestBody Permissions permissions) {
        
        
        return ResponseEntity.ok(permissionsService.updatePermissions(permissions,id));
    }
    
    
}
