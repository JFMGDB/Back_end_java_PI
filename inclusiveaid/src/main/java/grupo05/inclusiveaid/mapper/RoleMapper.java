package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.RoleDTO;
import grupo05.inclusiveaid.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<Role, RoleDTO> {
    @Override
    RoleDTO toDto(Role entity);

    @Override
    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntity(RoleDTO dto, @MappingTarget Role entity);
} 