package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.UserAdaptationSettingDTO;
import grupo05.inclusiveaid.entity.UserAdaptationSetting;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserAdaptationSettingMapper extends EntityMapper<UserAdaptationSetting, UserAdaptationSettingDTO> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "adaptation.id", target = "adaptationId")
    @Override
    UserAdaptationSettingDTO toDto(UserAdaptationSetting entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "adaptationId", target = "adaptation.id")
    @Override
    UserAdaptationSetting toEntity(UserAdaptationSettingDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "adaptationId", target = "adaptation.id")
    @Override
    void updateEntity(UserAdaptationSettingDTO dto, @MappingTarget UserAdaptationSetting entity);
} 