package grupo05.inclusiveaid.repository;


import grupo05.inclusiveaid.entity.UserAdaptationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para UserAdaptationSetting.
 */
public interface UserAdaptationSettingRepository extends JpaRepository<UserAdaptationSetting, Long> {
}