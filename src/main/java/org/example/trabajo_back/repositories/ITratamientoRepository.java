package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITratamientoRepository extends JpaRepository<Tratamiento, Long> {
    public List<Tratamiento>findByIdTratamiento(Long id);
}
