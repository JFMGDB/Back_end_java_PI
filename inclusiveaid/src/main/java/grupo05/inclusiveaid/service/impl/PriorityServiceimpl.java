package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.entity.Priority;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.PriorityMapper;
import grupo05.inclusiveaid.repository.PriorityRepository;
import grupo05.inclusiveaid.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;
    private final PriorityMapper priorityMapper;

    @Override
    @Transactional
    public PriorityDTO create(PriorityDTO dto) {
        Priority priority = priorityMapper.toEntity(dto);
        return priorityMapper.toDto(priorityRepository.save(priority));
    }

@Override 
public PriorityDTO getById(Long id) { 
return priorityMapper.toDto(priorityRepository.findById(id) 
.orElseThrow(() -> new ResourceNotFoundException("Priority 
not found"))); 
}

    @Override
    public Page<PriorityDTO> listAll(int page, int size) {
        return priorityRepository.findAll(PageRequest.of(page, size))
                .map(priorityMapper::toDto);
    }

    @Override
    @Transactional
    public PriorityDTO update(Long id, PriorityDTO dto) {
        if (!priorityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Priority not found");
        }
        Priority priority = priorityMapper.toEntity(dto);
        priority.setId(id);
        return priorityMapper.toDto(priorityRepository.save(priority));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!priorityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Priority not found");
        }
        priorityRepository.deleteById(id);
    }
}