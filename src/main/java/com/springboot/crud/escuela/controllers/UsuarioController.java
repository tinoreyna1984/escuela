package com.springboot.crud.escuela.controllers;

import com.springboot.crud.escuela.models.entities.Usuario;
import com.springboot.crud.escuela.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
