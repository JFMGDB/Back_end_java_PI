package grupo05.inclusiveaid.service;

import org.springframework.data.domain.Page;

/**
 * Interface genérica para serviços CRUD básicos usando DTOs.
 *
 * @param <D> Tipo do DTO
 */
public interface CrudService<D> {
    D create(D dto);
    D getById(Long id);
    Page<D> listAll(int page, int size);
    D update(Long id, D dto);
    void delete(Long id);
} 