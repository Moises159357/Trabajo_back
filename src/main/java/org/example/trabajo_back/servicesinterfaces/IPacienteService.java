package org.example.trabajo_back.servicesinterfaces;

import org.example.trabajo_back.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    public void insert(Paciente p);
    public List<Paciente> list();
    public Optional<Paciente> listId(Long id);
    public void update(Paciente p);
    public void delete(Long id);
}
