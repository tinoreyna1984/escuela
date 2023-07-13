package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
public class Rol implements Serializable {

    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rol")
    @NotBlank(message = "El nombre del rol no puede quedar en blanco")
    private String nombre;

    // "rol" es el objeto creado en Usuario (atributo)
    // un rol puede ser designado a varios usuarios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}
