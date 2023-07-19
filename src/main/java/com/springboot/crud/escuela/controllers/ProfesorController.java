package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Profesor;
import com.springboot.crud.escuela.models.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/profesores")
    public ResponseEntity<Page<Profesor>> listarProfesores(Pageable pageable){
        return ResponseEntity.ok(profesorRepository.findAll(pageable));
    }
}
