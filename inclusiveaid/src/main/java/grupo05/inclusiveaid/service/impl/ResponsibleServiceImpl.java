package grupo05.inclusiveaid.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo05.inclusiveaid.entity.Responsible;
import grupo05.inclusiveaid.repository.ResponsibleRepository;
import grupo05.inclusiveaid.service.ResponsibleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponsibleServiceImpl implements ResponsibleService{
    private final ResponsibleRepository responsibleRepository;

    @Override
    public Responsible createResponsible(Responsible responsible){
        return responsibleRepository.save(responsible);
    }

    @Override
    public List<Responsible> getAllResponsibles(){
        return responsibleRepository.findAll();
    }

    @Override
    public Responsible getResponsiblebyId(Long id){
        return responsibleRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));
    }

    @Override
    public Responsible updateResponsible(Long id, Responsible updateResponsible){
        Responsible responsible = getResponsiblebyId(id);

        responsible.setEmail(updateResponsible.getEmail() != null ? updateResponsible.getEmail() : responsible.getEmail());
        responsible.setName(updateResponsible.getName() != null ? updateResponsible.getName() : responsible.getName());
        responsible.setPassword(updateResponsible.getPassword() != null? updateResponsible.getPassword() : responsible.getPassword());

        return responsibleRepository.save(responsible);
    }

    @Override
    public void deleteResponsible(Long id){
        responsibleRepository.findById(id);
        responsibleRepository.deleteById(id);
    }
}
