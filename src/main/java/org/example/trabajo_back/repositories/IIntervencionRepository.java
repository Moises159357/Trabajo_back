package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIntervencionRepository extends JpaRepository<Intervencion, Long> {
}
