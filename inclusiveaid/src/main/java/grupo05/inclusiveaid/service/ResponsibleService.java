package grupo05.inclusiveaid.service;

import java.util.List;

import grupo05.inclusiveaid.entity.Responsible;

public interface ResponsibleService {
    List<Responsible> getAllResponsibles();
    Responsible getResponsiblebyId(Long id);
    Responsible createResponsible(Responsible responsible);
    Responsible updateResponsible(Long id, Responsible updateResponsible);
    void deleteResponsible(Long id);
}