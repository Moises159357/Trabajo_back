package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Tratamiento;

import java.util.List;
import java.util.Optional;

public interface ITratamientoService {
    public void insert (Tratamiento t);
    public List<Tratamiento>list();
    public void update(Tratamiento t);
    public Optional<Tratamiento>listId(Long id);
}
