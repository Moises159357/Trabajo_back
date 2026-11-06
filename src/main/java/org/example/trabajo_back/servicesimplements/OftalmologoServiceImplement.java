package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Oftalmologo;
import org.example.trabajo_back.repositories.IOftalmologoRepository;
import org.example.trabajo_back.servicesinterfaces.IOftalmologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OftalmologoServiceImplement implements IOftalmologoService {
    private final IOftalmologoRepository oR;

    public OftalmologoServiceImplement(IOftalmologoRepository oR) {
        this.oR = oR;
    }

    @Override
    public void insert(Oftalmologo o) { oR.save(o); }

    @Override
    public List<Oftalmologo> list() { return oR.findAll(); }

    @Override
    public Optional<Oftalmologo> listId(Long id) { return oR.findById(id); }

    @Override
    public void update(Oftalmologo o) { oR.save(o); }

    @Override
    public void delete(Long id) { oR.deleteById(id); }
}