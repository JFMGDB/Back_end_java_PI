package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.CategoryDTO;
import grupo05.inclusiveaid.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper para entidade Categoria.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDTO dto);

    CategoryDTO toDto(Category entity);

    void updateEntity(CategoryDTO dto, @MappingTarget Category entity);
}