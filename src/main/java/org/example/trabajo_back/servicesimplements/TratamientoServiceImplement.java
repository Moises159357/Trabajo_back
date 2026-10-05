package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Tratamiento;
import org.example.trabajo_back.repositories.ITratamientoRepository;
import org.example.trabajo_back.servicesinterfaces.ITratamientoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamientoServiceImplement implements ITratamientoService {
    private final ITratamientoRepository tR;

    public TratamientoServiceImplement(ITratamientoRepository tR) {
        this.tR = tR;
    }

    @Override
    public void insert(Tratamiento t) {
        tR.save(t);
    }

    @Override
    public List<Tratamiento> list() {
        return tR.findAll();
    }

    @Override
    public void update(Tratamiento t) {
        tR.save(t);
    }

    @Override
    public Optional<Tratamiento> listId(Long id) {
        return tR.findById(id);
    }
}
