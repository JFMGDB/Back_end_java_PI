package grupo05.inclusiveaid.mapper;

import org.mapstruct.MappingTarget;

/**
 * Interface genérica para mapeadores MapStruct.
 *
 * @param <E> Entidade
 * @param <D> DTO
 */
public interface EntityMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateEntity(D dto, @MappingTarget E entity);
} 