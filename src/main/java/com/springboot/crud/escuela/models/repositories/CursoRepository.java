package com.springboot.crud.escuela.models.repositories;

import com.springboot.crud.escuela.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
