package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Usuario;
import com.springboot.crud.escuela.models.repositories.UsuarioRepository;
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
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping({"/index", "/home", "/", ""})
    public String index(){
        return "index";
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Page<Usuario>> listarUsuarios (Pageable pageable){
        return ResponseEntity.ok(usuarioRepository.findAll(pageable));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id){
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            usuario = usuarioRepository.findById(id).get();
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*if(usuario == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El usuario ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){
        Usuario usuarioNuevo = null;
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
            usuarioNuevo = usuarioRepository.save(usuario);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El usuario ha sido creado con éxito");
        response.put("nota", usuarioNuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        Usuario usuarioActual = usuarioRepository.findById(id).get();
        Usuario usuarioEditado = null;
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

        /*if(usuarioActual == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El usuario ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        try {
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setDocIdentidad(usuario.getDocIdentidad());
            usuarioActual.setUsername(usuario.getUsername());
            usuarioActual.setEmail(usuario.getEmail());
            usuarioEditado = usuarioRepository.save(usuarioActual);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el update en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El usuario ha sido editado con éxito");
        response.put("nota", usuarioEditado);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();

        /*if(usuario == null){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje", "El usuario ".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        try {
            usuarioRepository.deleteById(id);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El usuario ha sido eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }
}
