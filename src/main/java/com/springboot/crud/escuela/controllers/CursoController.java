package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Curso;
import com.springboot.crud.escuela.models.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/cursos")
    public ResponseEntity<Page<Curso>> listarCursos(Pageable pageable){
        return ResponseEntity.ok(cursoRepository.findAll(pageable));
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id){
        Curso curso = null;
        Map<String, Object> response = new HashMap<>();

        try {
            curso = cursoRepository.findById(id).get();
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Curso>(curso, HttpStatus.OK);
    }

    @PostMapping("/cursos")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody Curso curso, BindingResult result){
        Curso cursoNuevo = null;
        Map<String, Object> response = new HashMap<>();

        // proceso de validación
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cursoNuevo = cursoRepository.save(curso);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El curso ha sido creado con éxito");
        response.put("nota", cursoNuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        Curso cursoActual = cursoRepository.findById(id).get();
        Curso cursoEditado = null;
        Map<String, Object> response = new HashMap<>();

        // proceso de validación
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cursoActual.setNombre(curso.getNombre());
            cursoActual.setHorasTeoria(curso.getHorasTeoria());
            cursoActual.setHorasPractica(curso.getHorasPractica());
            cursoEditado = cursoRepository.save(cursoActual);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el update en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El curso ha sido editado con éxito");
        response.put("nota", cursoEditado);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id){
        Curso usuario = cursoRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();

        try {
            cursoRepository.deleteById(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El curso ha sido eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }
}
