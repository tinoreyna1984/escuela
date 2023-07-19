package com.springboot.crud.escuela.models.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Usuario implements Serializable {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // tablas externas de estudiante y profesor
    //@JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Estudiante estudiante;
    //@JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profesor profesor;

    // hace referencia a la clase Rol (muchos usuarios para un rol)
    /*@ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;*/

    private String nombre;

    private String apellido;

    @Column(name = "doc_identidad")
    private String docIdentidad;

    @Column(name = "fecha_nacimiento")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaNacimiento;

    private String username;

    @Size(min = 6, max = 16, message = "La clave debe constar de 6 a 16 caracteres")
    private String password;

    @Email(message = "No válido. Asegúrate de que sea un correo electrónico")
    private String email;

    @Column(name = "fecha_creacion", insertable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Guayaquil")
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Guayaquil")
    private Date fechaActualizacion;

}
