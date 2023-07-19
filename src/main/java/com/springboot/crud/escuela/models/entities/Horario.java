package com.springboot.crud.escuela.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
