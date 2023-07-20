package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Profesor;
import com.springboot.crud.escuela.models.repositories.ProfesorRepository;
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
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/profesores")
    public ResponseEntity<Page<Profesor>> listarProfesores(Pageable pageable){
        return ResponseEntity.ok(profesorRepository.findAll(pageable));
    }


    @GetMapping("/profesores/{id}")
    public ResponseEntity<?> buscarProfesor(@PathVariable Long id){
        Profesor profesor = null;

        Map<String, Object> response = new HashMap<>();

        try {
            profesor = profesorRepository.findById(id).get();
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*if(estudiante == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El profesor ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
    }

    @PostMapping("/profesores")
    public ResponseEntity<?> guardarProfesor(@Valid @RequestBody Profesor profesor, BindingResult result){
        Profesor profesorNuevo = null;
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
            profesorNuevo = profesorRepository.save(profesor);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El profesor ha sido creado con éxito");
        response.put("nota", profesorNuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<?> borrarProfesor(@PathVariable Long id){
        Profesor profesor = profesorRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();

        /*if(estudiante == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El estudiante ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        try {
            profesorRepository.deleteById(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El profesor ha sido eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }
}
