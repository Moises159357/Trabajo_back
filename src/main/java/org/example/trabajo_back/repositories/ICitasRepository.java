package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICitasRepository extends JpaRepository<Citas, Long> {
    @Query(value = "SELECT p.id_paciente, p.nombre, COUNT(c.id_citas) AS total_citas\n" +
            "FROM paciente p\n" +
            "INNER JOIN citas c ON p.id_paciente = c.id_paciente\n" +
            "GROUP BY p.id_paciente, p.nombre", nativeQuery = true)
    List<Object[]> getTotalCitasPorPaciente();

    @Query(value = "SELECT p.id_paciente, p.nombre, o.nombres, COUNT(*) AS veces " +
            "FROM paciente p " +
            "INNER JOIN citas c ON p.id_paciente = c.id_paciente " +
            "INNER JOIN oftalmologo o ON c.id_oftalmologo = o.id_oftalmologo " +
            "GROUP BY p.id_paciente, p.nombre, o.id_oftalmologo, o.nombres",
            nativeQuery = true)
    List<Object[]> getCitasYOftalmologosFrecuente();

}
