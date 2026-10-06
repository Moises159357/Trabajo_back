package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Recetas;
import org.example.trabajo_back.repositories.IRecetasRepository;
import org.example.trabajo_back.servicesinterfaces.IRecetasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetasServiceImplement implements IRecetasService {
    private final IRecetasRepository rR;

    public RecetasServiceImplement(IRecetasRepository rR) {
        this.rR = rR;
    }

    @Override
    public void insert(Recetas r) {
        rR.save(r);
    }

    @Override
    public List<Recetas> list() {
        return rR.findAll();
    }

    @Override
    public void update(Recetas r) {
        rR.save(r);
    }

    @Override
    public Optional<Recetas> listId(Long id) {
        return rR.findById(id);
    }
}
