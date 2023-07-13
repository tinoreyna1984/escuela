package com.springboot.crud.escuela.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
public class Profesor implements Serializable {
    @Id
    @Column(name = "profesor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    // para la relación muchos a muchos con cursos
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "horario",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;
}
