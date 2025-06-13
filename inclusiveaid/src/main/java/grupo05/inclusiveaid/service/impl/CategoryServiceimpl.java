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
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação de {@link CategoryService}.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

@Override 
public CategoryDTO getById(Long id) { 
return categoryMapper.toDto(categoryRepository.findById(id) 
.orElseThrow(() -> new ResourceNotFoundException("Category 
not found"))); 
}

    @Override
    public Page<CategoryDTO> listAll(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size))
                .map(categoryMapper::toDto);
    }

    @Override
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        Category category = categoryMapper.toEntity(dto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}