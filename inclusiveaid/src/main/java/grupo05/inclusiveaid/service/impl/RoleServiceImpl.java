package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.RoleDTO;
import grupo05.inclusiveaid.entity.Role;
import grupo05.inclusiveaid.mapper.RoleMapper;
import grupo05.inclusiveaid.repository.RoleRepository;
import grupo05.inclusiveaid.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractCrudServiceImpl<Role, RoleDTO> implements RoleService {
    public RoleServiceImpl(RoleRepository repo, RoleMapper mapper) {
        super(repo, mapper);
    }
} 