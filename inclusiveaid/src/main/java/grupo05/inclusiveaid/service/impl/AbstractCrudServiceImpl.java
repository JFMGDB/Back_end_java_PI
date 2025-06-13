package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.EntityMapper;
import grupo05.inclusiveaid.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação base para serviços CRUD.
 *
 * @param <E> Entidade
 * @param <D> DTO
 */
@RequiredArgsConstructor
@Transactional
public abstract class AbstractCrudServiceImpl<E, D> implements CrudService<D> {

    private final JpaRepository<E, Long> repo;
    private final EntityMapper<E, D> mapper;

    @Override
    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public D getById(Long id) {
        return mapper.toDto(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Override
    public Page<D> listAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    public D update(Long id, D dto) {
        E entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        mapper.updateEntity(dto, entity);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        repo.deleteById(id);
    }
} 