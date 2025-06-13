package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.TagDTO;
import grupo05.inclusiveaid.entity.Tag;
import grupo05.inclusiveaid.mapper.TagMapper;
import grupo05.inclusiveaid.repository.TagRepository;
import grupo05.inclusiveaid.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends AbstractCrudServiceImpl<Tag, TagDTO> implements TagService {
    public TagServiceImpl(TagRepository repo, TagMapper mapper) {
        super(repo, mapper);
    }
} 