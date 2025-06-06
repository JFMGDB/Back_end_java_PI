package grupo05.inclusiveaid.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo05.inclusiveaid.entity.Permissions;
import grupo05.inclusiveaid.repository.PermissionsRepository;
import grupo05.inclusiveaid.service.PermissionsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionsServiceImpl implements PermissionsService {
    private final PermissionsRepository permissionsRepository;
    
    @Override
    public Permissions creatPermissions(Permissions permissions){
        return permissionsRepository.save(permissions);
    }
    
    @Override
    public Permissions updatePermissions(Permissions updatePermissions, Long id){
        Permissions getPermissions = getPermissionsById(id);
        getPermissions.setAgenda(updatePermissions.getAgenda() == null ? getPermissions.getAgenda() : updatePermissions.getAgenda());
        getPermissions.setCamera(updatePermissions.getCamera() == null ? getPermissions.getCamera() : updatePermissions.getCamera());
        getPermissions.setContact(updatePermissions.getContact() == null ? getPermissions.getContact() : updatePermissions.getContact());
        getPermissions.setLocation(updatePermissions.getLocation() == null ? getPermissions.getLocation() : updatePermissions.getLocation());
        getPermissions.setStorage(updatePermissions.getStorage() == null ? getPermissions.getStorage() : updatePermissions.getStorage());
        return permissionsRepository.save(getPermissions);
    }
    
    @Override
    public List<Permissions> getAllPermissions(){
        return permissionsRepository.findAll();
    }
    
    @Override
    public Permissions getPermissionsById(Long id){
       return permissionsRepository
            .findById(id)
            .orElseThrow(()->new EntityNotFoundException("Permissão não encontrada."));
    }
    
    @Override
    public void deletePermissionsById(Long id){
        getPermissionsById(id);
        permissionsRepository.deleteById(id);
    }
}
