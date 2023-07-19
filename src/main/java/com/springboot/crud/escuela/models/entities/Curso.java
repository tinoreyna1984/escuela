package com.springboot.crud.escuela.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonIgnore
    @ManyToMany(mappedBy = "cursos")
    private List<Estudiante> estudiantes;

    // para la relación muchos a muchos con profesores
    @JsonIgnore
    @ManyToMany(mappedBy = "cursos")
    private List<Profesor> profesores;

}
