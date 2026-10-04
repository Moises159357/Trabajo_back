package org.example.trabajo_back.repositories;

import org.example.trabajo_back.dtos.ReporteLenteDTO;
import org.example.trabajo_back.entities.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleRepository extends JpaRepository<Detalle, Long> {

    // Query 1: todos los detalles de un historial clínico
    @Query("SELECT d FROM Detalle d WHERE d.idHistorial = :idHistorial ORDER BY d.idDetalle")
    List<Detalle> buscarPorHistorial(@Param("idHistorial") Long idHistorial);

    // Query 2: lentes más indicados (cantidad de detalles por lente, de mayor a menor)
    @Query("SELECT new org.example.trabajo_back.dtos.ReporteLenteDTO(l.idLente, l.marca, l.modelo, l.precio, COUNT(d)) " +
            "FROM Detalle d JOIN d.lente l " +
            "GROUP BY l.idLente, l.marca, l.modelo, l.precio " +
            "ORDER BY COUNT(d) DESC")
    List<ReporteLenteDTO> lentesMasUsados();
}
