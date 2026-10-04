package org.example.trabajo_back.repositories;

import org.example.trabajo_back.entities.Lente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ILenteRepository extends JpaRepository<Lente, Long> {

    // Query 1: lentes de una marca (sin distinguir mayúsculas)
    @Query("SELECT l FROM Lente l WHERE LOWER(l.marca) = LOWER(:marca) ORDER BY l.precio ASC")
    List<Lente> buscarPorMarca(@Param("marca") String marca);

    // Query 2: lentes dentro de un rango de precios
    @Query("SELECT l FROM Lente l WHERE l.precio BETWEEN :min AND :max ORDER BY l.precio ASC")
    List<Lente> buscarPorRangoPrecio(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
