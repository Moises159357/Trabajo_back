package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Citas;

import java.util.List;
import java.util.Optional;

public interface ICitasService {
    public void insertar(Citas c);
    public List<Citas> listar();
    public Optional<Citas> listarId(Long id);
    public void actualizar(Citas c);
    public void eliminar(Long id);
}
