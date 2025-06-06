package grupo05.inclusiveaid.service;

import java.util.List;

import grupo05.inclusiveaid.entity.Permissions;

public interface PermissionsService {
    Permissions creatPermissions(Permissions permisions);
    Permissions updatePermissions(Permissions permisions, Long id);
    List<Permissions> getAllPermissions();
    Permissions getPermissionsById(Long id);
    void deletePermissionsById(Long id);
}
