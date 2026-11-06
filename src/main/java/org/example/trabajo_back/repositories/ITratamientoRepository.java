package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITratamientoRepository extends JpaRepository<Tratamiento, Long> {
    @Query(value = "SELECT t.id_tratamiento, t.nombre, t.indicaciones, " +
            "t.duracion_dias, t.tipo, t.estado, t.fecha_registro\n" +
            "FROM tratamientos t\n" +
            "WHERE LOWER(t.estado) = LOWER(:estado)", nativeQuery = true)
    List<Object[]> getTratamientosPorEstado(String estado);

}
