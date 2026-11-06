package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Recetas;

import java.util.List;
import java.util.Optional;

public interface IRecetasService{
    public void insert (Recetas r);
    public List<Recetas> list();
    public void update(Recetas r);
    public Optional<Recetas> listId(Long id);
}
