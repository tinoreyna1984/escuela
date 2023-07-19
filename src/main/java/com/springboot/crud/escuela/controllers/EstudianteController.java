package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Estudiante;
import com.springboot.crud.escuela.models.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/estudiantes")
    public ResponseEntity<Page<Estudiante>> listarEstudiantes(Pageable pageable){
        return ResponseEntity.ok(estudianteRepository.findAll(pageable));
    }
}
