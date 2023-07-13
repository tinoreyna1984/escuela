package com.springboot.crud.escuela.models.repositories;

import com.springboot.crud.escuela.models.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository  extends JpaRepository<Profesor, Long> {
}
