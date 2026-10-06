package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Oftalmologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOftalmologoRepository extends JpaRepository<Oftalmologo, Long> {
    // Metodo derivado para buscar oftalmólogos disponibles
    List<Oftalmologo> findByDisponibilidadTrue();
}