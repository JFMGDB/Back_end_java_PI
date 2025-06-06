package grupo05.inclusiveaid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo05.inclusiveaid.entity.Responsible;
import grupo05.inclusiveaid.service.ResponsibleService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/api/responsible")
@RestController
@RequiredArgsConstructor
public class ResponsibleController {
    private final ResponsibleService responsibleService;

    @GetMapping
    public ResponseEntity<List<Responsible>> getAllResponsible(){
        List<Responsible> responsibles = responsibleService.getAllResponsibles();
        if(responsibles.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(responsibles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsible> getResponsibleById(@PathVariable Long id) {
        return ResponseEntity.ok(responsibleService.getResponsiblebyId(id));
    }
    
    @PostMapping
    public ResponseEntity<Responsible> createResponsible(@RequestBody Responsible responsible) {
        return ResponseEntity.ok(responsibleService.createResponsible(responsible));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsible> updateResponsivle(@PathVariable Long id, @RequestBody Responsible updateResponsible){
        return ResponseEntity.ok(responsibleService.updateResponsible(id, updateResponsible));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResponsible(@PathVariable Long id){
        responsibleService.deleteResponsible(id);
        return ResponseEntity.ok("Responsável deletado");
    }
    
}
