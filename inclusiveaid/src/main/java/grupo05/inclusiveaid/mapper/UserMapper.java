package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.UserDTO;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.entity.DisabilityType;
import org.mapstruct.*;
import java.util.*;

/**
 * Mapeia User ↔ UserDTO, tratando ManyToMany de DisabilityType.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "role.id", target = "roleId")
  @Mapping(target = "disabilityTypeIds", expression = "java(toIds(user.getDisabilityTypes()))")
  UserDTO toDto(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "roleId", target = "role.id")
  @Mapping(target = "disabilityTypes", expression = "java(toEntities(dto.getDisabilityTypeIds()))")
  @Mapping(target = "adaptations", ignore = true)
  User toEntity(UserDTO dto);

  default Set<Long> toIds(Set<DisabilityType> types) {
    if (types == null) return Collections.emptySet();
    Set<Long> ids = new HashSet<>();
    types.forEach(t -> ids.add(t.getId()));
    return ids;
  }

  default Set<DisabilityType> toEntities(Set<Long> ids) {
    if (ids == null) return Collections.emptySet();
    Set<DisabilityType> set = new HashSet<>();
    ids.forEach(id -> {
      DisabilityType dt = new DisabilityType();
      dt.setId(id);
      set.add(dt);
    });
    return set;
  }
}