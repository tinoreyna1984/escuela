package com.springboot.crud.escuela.models.repositories;

import com.springboot.crud.escuela.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
