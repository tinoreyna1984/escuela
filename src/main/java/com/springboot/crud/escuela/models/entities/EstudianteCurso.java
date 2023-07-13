package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "estudiante_curso")
@Getter
@Setter
@NoArgsConstructor
public class EstudianteCurso implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // columnas para la relación muchos a muchos
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}
