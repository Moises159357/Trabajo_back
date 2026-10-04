package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Lente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ILenteService {
    void insertar(Lente l);
    List<Lente> listar();
    Optional<Lente> listarId(Long id);
    void actualizar(Lente l);
    void eliminar(Long id);
    List<Lente> buscarPorMarca(String marca);
    List<Lente> buscarPorRangoPrecio(BigDecimal min, BigDecimal max);
}
