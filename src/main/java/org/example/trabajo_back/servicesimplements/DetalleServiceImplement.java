package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.dtos.ReporteLenteDTO;
import org.example.trabajo_back.entities.Detalle;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.repositories.IDetalleRepository;
import org.example.trabajo_back.repositories.IIntervencionRepository;
import org.example.trabajo_back.repositories.ILenteRepository;
import org.example.trabajo_back.repositories.ITratamientoRepository;
import org.example.trabajo_back.servicesinterfaces.IDetalleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleServiceImplement implements IDetalleService {
    private final IDetalleRepository dR;
    private final ILenteRepository lR;
    private final ITratamientoRepository tR;
    private final IIntervencionRepository iR;

    public DetalleServiceImplement(IDetalleRepository dR, ILenteRepository lR,
                                   ITratamientoRepository tR, IIntervencionRepository iR) {
        this.dR = dR;
        this.lR = lR;
        this.tR = tR;
        this.iR = iR;
    }

    // Reemplaza las referencias "solo con id" por las entidades reales; si no existen, lanza 404.
    private void resolverReferencias(Detalle d) {
        Long idL = d.getLente().getIdLente();
        Long idT = d.getTratamiento().getIdTratamiento();
        Long idI = d.getIntervencion().getIdIntervencion();
        d.setLente(lR.findById(idL).orElseThrow(() ->
                new ResourceNotFoundException("No existe el lente con el id: " + idL)));
        d.setTratamiento(tR.findById(idT).orElseThrow(() ->
                new ResourceNotFoundException("No existe el tratamiento con el id: " + idT)));
        d.setIntervencion(iR.findById(idI).orElseThrow(() ->
                new ResourceNotFoundException("No existe la intervencion con el id: " + idI)));
    }

    @Override
    public void insertar(Detalle d) {
        resolverReferencias(d);
        dR.save(d);
    }

    @Override
    public List<Detalle> listar() { return dR.findAll(); }

    @Override
    public Optional<Detalle> listarId(Long id) { return dR.findById(id); }

    @Override
    public void actualizar(Detalle d) {
        resolverReferencias(d);
        dR.save(d);
    }

    @Override
    public void eliminar(Long id) { dR.deleteById(id); }

    @Override
    public List<Detalle> buscarPorHistorial(Long idHistorial) { return dR.buscarPorHistorial(idHistorial); }

    @Override
    public List<ReporteLenteDTO> lentesMasUsados() { return dR.lentesMasUsados(); }
}
