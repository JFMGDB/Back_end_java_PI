package com.seuusuario.seuprojeto.repository;

import com.seuusuario.seuprojeto.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}