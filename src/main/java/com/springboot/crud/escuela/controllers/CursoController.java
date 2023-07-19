package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Curso;
import com.springboot.crud.escuela.models.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/cursos")
    public ResponseEntity<Page<Curso>> listarCursos(Pageable pageable){
        return ResponseEntity.ok(cursoRepository.findAll(pageable));
    }
}
