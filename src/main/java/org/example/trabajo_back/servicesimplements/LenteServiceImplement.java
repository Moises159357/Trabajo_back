package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Lente;
import org.example.trabajo_back.repositories.ILenteRepository;
import org.example.trabajo_back.servicesinterfaces.ILenteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LenteServiceImplement implements ILenteService {
    private final ILenteRepository lR;

    public LenteServiceImplement(ILenteRepository lR) {
        this.lR = lR;
    }

    @Override
    public void insertar(Lente l) { lR.save(l); }

    @Override
    public List<Lente> listar() { return lR.findAll(); }

    @Override
    public Optional<Lente> listarId(Long id) { return lR.findById(id); }

    @Override
    public void actualizar(Lente l) { lR.save(l); }

    @Override
    public void eliminar(Long id) { lR.deleteById(id); }

    @Override
    public List<Lente> buscarPorMarca(String marca) { return lR.buscarPorMarca(marca); }

    @Override
    public List<Lente> buscarPorRangoPrecio(BigDecimal min, BigDecimal max) {
        return lR.buscarPorRangoPrecio(min, max);
    }
}
