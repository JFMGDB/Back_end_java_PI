package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.CategoryDTO;
import grupo05.inclusiveaid.entity.Category;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.CategoryMapper;
import grupo05.inclusiveaid.repository.CategoryRepository;
import grupo05.inclusiveaid.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Serviço CRUD para Category.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category entity = mapper.toEntity(dto);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public CategoryDTO getById(Long id) {
        Category entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapper.toDto(entity);
    }

    @Override
    public Page<CategoryDTO> listAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        mapper.updateEntity(dto, entity);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        repo.deleteById(id);
    }
} 