package com.springboot.crud.escuela.models.repositories;

import com.springboot.crud.escuela.models.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT h.horario_id, c.nombre_curso, c.horas_teoria, c.horas_practica, u.nombre, u.apellido\n" +
                    "from horario h, curso c, profesor p, usuario u\n" +
                    "where h.profesor_id = p.profesor_id\n" +
                    "and h.curso_id = c.curso_id\n" +
                    "and p.usuario_id = u.usuario_id"
    )
    public List<Map<String, Object>> obtieneHorarios();

}
