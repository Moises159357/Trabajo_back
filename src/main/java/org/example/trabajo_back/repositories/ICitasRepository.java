package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitasRepository extends JpaRepository<Citas, Long> {
}
