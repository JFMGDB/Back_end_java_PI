package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.ResponsibleDTO;
import grupo05.inclusiveaid.entity.Responsible;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResponsibleMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "user.id", target = "userId")
    ResponsibleDTO toDTO(Responsible entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Responsible toEntity(ResponsibleDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO(ResponsibleDTO dto, @MappingTarget Responsible entity);
} 