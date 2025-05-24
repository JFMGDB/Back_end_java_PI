package com.seuusuario.seuprojeto.repository;

import com.seuusuario.seuprojeto.model.Necessidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessidadeRepository extends JpaRepository<Necessidade, Long> {
}