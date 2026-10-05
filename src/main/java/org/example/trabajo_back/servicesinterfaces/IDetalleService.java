package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.dtos.ReporteLenteDTO;
import org.example.trabajo_back.entities.Detalle;

import java.util.List;
import java.util.Optional;

public interface IDetalleService {
    void insertar(Detalle d);
    List<Detalle> listar();
    Optional<Detalle> listarId(Long id);
    void actualizar(Detalle d);
    void eliminar(Long id);
    List<Detalle> buscarPorHistorial(Long idHistorial);
    List<ReporteLenteDTO> lentesMasUsados();
}
