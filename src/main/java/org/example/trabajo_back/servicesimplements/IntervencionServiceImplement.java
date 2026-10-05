package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.repositories.IIntervencionRepository;
import org.example.trabajo_back.servicesinterfaces.IIntervencionService;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntervencionServiceImplement implements IIntervencionService {
    private final IIntervencionRepository iR;

    public IntervencionServiceImplement(IIntervencionRepository iR) {
        this.iR = iR;
    }


    @Override
    public void insert(Intervencion i) {
        iR.save(i);
    }

    @Override
    public List<Intervencion> list() {
        return iR.findAll();
    }

    @Override
    public void update(Intervencion i) {
        iR.save(i);
    }

    @Override
    public Optional<Intervencion> listId(Long id) {
        return iR.findById(id);
    }
}
