package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "horario")
@Getter
@Setter
@NoArgsConstructor
public class Horario {
    @Id
    @Column(name = "horario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // columnas para la relación muchos a muchos
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
