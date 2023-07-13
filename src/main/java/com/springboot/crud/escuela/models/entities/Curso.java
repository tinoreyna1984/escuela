package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
public class Curso implements Serializable {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_curso")
    private String nombre;

    @Column(name = "horas_teoria")
    private Integer horasTeoria;

    @Column(name = "horas_practica")
    private Integer horasPractica;

    // para la relación muchos a muchos con estudiantes
    @ManyToMany(mappedBy = "cursos")
    private List<Estudiante> estudiantes;

    // para la relación muchos a muchos con profesores
    @ManyToMany(mappedBy = "cursos")
    private List<Profesor> profesores;

}
