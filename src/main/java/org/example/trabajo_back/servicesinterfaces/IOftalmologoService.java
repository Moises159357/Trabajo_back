package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Oftalmologo;

import java.util.List;
import java.util.Optional;

public interface IOftalmologoService {
    public void insert(Oftalmologo o);
    public List<Oftalmologo> list();
    public Optional<Oftalmologo> listId(Long id);
    public void update(Oftalmologo o);
    public void delete(Long id);
}