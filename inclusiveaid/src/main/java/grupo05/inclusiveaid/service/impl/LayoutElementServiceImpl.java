package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LayoutElementDTO;
import grupo05.inclusiveaid.entity.LayoutElement;
import grupo05.inclusiveaid.mapper.LayoutElementMapper;
import grupo05.inclusiveaid.repository.LayoutElementRepository;
import grupo05.inclusiveaid.service.LayoutElementService;
import org.springframework.stereotype.Service;

@Service
public class LayoutElementServiceImpl extends AbstractCrudServiceImpl<LayoutElement, LayoutElementDTO>
        implements LayoutElementService {
    public LayoutElementServiceImpl(LayoutElementRepository repo, LayoutElementMapper mapper) {
        super(repo, mapper);
    }
} 