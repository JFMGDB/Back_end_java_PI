package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.PermissionsDTO;
import grupo05.inclusiveaid.entity.Permissions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionsMapper {
    
    @Mapping(target = "id", ignore = true)
    Permissions toEntity(PermissionsDTO dto);

    PermissionsDTO toDTO(Permissions entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(PermissionsDTO dto, @MappingTarget Permissions entity);
} 