package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Estudiante;
import com.springboot.crud.escuela.models.repositories.EstudianteRepository;
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
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/estudiantes")
    public ResponseEntity<Page<Estudiante>> listarEstudiantes(Pageable pageable){
        return ResponseEntity.ok(estudianteRepository.findAll(pageable));
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<?> buscarEstudiante(@PathVariable Long id){
        Estudiante estudiante = null;

        Map<String, Object> response = new HashMap<>();

        try {
            estudiante = estudianteRepository.findById(id).get();
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*if(estudiante == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El estudiante ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
    }

    @PostMapping("/estudiantes")
    public ResponseEntity<?> guardarEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult result){
        Estudiante estudianteNuevo = null;
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
            estudianteNuevo = estudianteRepository.save(estudiante);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El estudiante ha sido creado con éxito");
        response.put("nota", estudianteNuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<?> borrarEstudiante(@PathVariable Long id){
        Estudiante estudiante = estudianteRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();

        /*if(estudiante == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El estudiante ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        try {
            estudianteRepository.deleteById(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El estudiante ha sido eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }
}
