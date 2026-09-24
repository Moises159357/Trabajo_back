package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Paciente;
import org.example.trabajo_back.repositories.IPacienteRepository;
import org.example.trabajo_back.servicesinterfaces.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImplement implements IPacienteService {
    private final IPacienteRepository pR;

    public PacienteServiceImplement(IPacienteRepository pR) {this.pR = pR;}

    @Override
    public void insert(Paciente p) {pR.save(p);}

    @Override
    public List<Paciente> list() {return pR.findAll();}

    @Override
    public Optional<Paciente> listId(Long id) {return pR.findById(id);}

    @Override
    public void update(Paciente p) {pR.save(p);}

    @Override
    public void delete(Long id) {pR.deleteById(id);}
}
