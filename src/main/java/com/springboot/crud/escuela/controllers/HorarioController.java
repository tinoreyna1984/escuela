package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Horario;
import com.springboot.crud.escuela.models.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HorarioController {

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping("/horarios")
    /*public ResponseEntity<Page<Horario>> listarHorarios(Pageable pageable){
        return ResponseEntity.ok(horarioRepository.findAll(pageable));
    }*/
    public List<Map<String, Object>> listarHorarios(){
        return horarioRepository.obtieneHorarios();
    }
}
