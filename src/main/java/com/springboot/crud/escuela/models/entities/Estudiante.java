package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
public class Estudiante implements Serializable {

    @Id
    @Column(name = "estudiante_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // para la relación muchos a muchos con cursos
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;
}
