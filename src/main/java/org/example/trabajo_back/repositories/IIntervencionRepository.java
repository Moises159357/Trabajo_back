package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IIntervencionRepository extends JpaRepository<Intervencion, Long> {
    @Query(value = "SELECT i.id_intervencion, i.id_venta, i.tipo, i.fecha, " +
            "i.descripcion, i.indicaciones\n" +
            "FROM intervenciones i\n" +
            "WHERE i.fecha BETWEEN :fechaInicio AND :fechaFin\n" +
            "ORDER BY i.fecha", nativeQuery = true)
    List<Object[]> obtenerIntervencionesPorFechas(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    @Query(value = "SELECT i.id_intervencion, i.id_venta, i.tipo, " +
            "i.fecha, i.descripcion, i.indicaciones\n" +
            "FROM intervenciones i\n" +
            "WHERE LOWER(i.tipo) = LOWER(:tipo)", nativeQuery = true)
    List<Object[]> obtenerIntervencionesPorTipo(String tipo);
}
