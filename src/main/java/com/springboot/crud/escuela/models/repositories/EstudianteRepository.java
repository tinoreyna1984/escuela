package com.springboot.crud.escuela.models.repositories;

import com.springboot.crud.escuela.models.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
