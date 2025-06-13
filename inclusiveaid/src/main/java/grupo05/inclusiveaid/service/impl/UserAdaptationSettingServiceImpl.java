package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.UserAdaptationSettingDTO;
import grupo05.inclusiveaid.entity.UserAdaptationSetting;
import grupo05.inclusiveaid.mapper.UserAdaptationSettingMapper;
import grupo05.inclusiveaid.repository.UserAdaptationSettingRepository;
import grupo05.inclusiveaid.service.UserAdaptationSettingService;
import org.springframework.stereotype.Service;

@Service
public class UserAdaptationSettingServiceImpl extends AbstractCrudServiceImpl<UserAdaptationSetting, UserAdaptationSettingDTO>
        implements UserAdaptationSettingService {
    public UserAdaptationSettingServiceImpl(UserAdaptationSettingRepository repo, UserAdaptationSettingMapper mapper) {
        super(repo, mapper);
    }
} 