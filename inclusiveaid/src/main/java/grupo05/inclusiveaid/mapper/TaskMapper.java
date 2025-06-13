package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.TaskDTO;
import grupo05.inclusiveaid.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Interface responsável pelo mapeamento entre entidades Task e DTOs.
 * Utiliza MapStruct para gerar automaticamente a implementação do mapeamento.
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {
    /**
     * Converte um DTO para uma entidade Task.
     * @param dto DTO a ser convertido
     * @return Entidade Task
     */
    @Mapping(target = "responsible", ignore = true)
    Task toEntity(TaskDTO dto);
    
    /**
     * Converte uma entidade Task para um DTO.
     * @param entity Entidade a ser convertida
     * @return DTO da tarefa
     */
    @Mapping(source = "responsible.id", target = "responsibleId")
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TaskDTO toDto(Task entity);
    
    /**
     * Atualiza uma entidade Task com os dados do DTO.
     * @param dto DTO com os novos dados
     * @param entity Entidade a ser atualizada
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "responsible", ignore = true)
    void updateEntity(TaskDTO dto, @MappingTarget Task entity);
} 