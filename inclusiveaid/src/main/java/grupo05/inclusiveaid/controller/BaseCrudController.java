package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador base com operações CRUD genéricas.
 * Subclasses devem definir @RequestMapping no nível de classe e, opcionalmente,
 * adicionar anotações Swagger específicas.
 *
 * @param <D> Tipo do DTO
 */
public abstract class BaseCrudController<D> {

    protected final CrudService<D> service;

    protected BaseCrudController(CrudService<D> service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar recurso", description = "Cria um novo recurso")
    public ResponseEntity<D> create(@Validated @RequestBody D dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter recurso por ID", description = "Recupera um recurso específico pelo seu ID")
    public ResponseEntity<D> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar recursos", description = "Lista recursos de forma paginada")
    public ResponseEntity<Page<D>> list(
            @Parameter(description = "Número da página (começa em 0)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Número de itens por página")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.listAll(page, size));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar recurso", description = "Atualiza um recurso existente")
    public ResponseEntity<D> update(@PathVariable Long id, @Validated @RequestBody D dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir recurso", description = "Remove um recurso pelo ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 