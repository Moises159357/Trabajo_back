package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Intervencion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IIntervencionService {
    public void insert (Intervencion i);
    public List<Intervencion> list();
    public void update(Intervencion i);
    public Optional<Intervencion> listId(Long id);
    List<Object[]> obtenerIntervencionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
    List<Object[]> obtenerIntervencionesPorTipo(String tipo);
}
