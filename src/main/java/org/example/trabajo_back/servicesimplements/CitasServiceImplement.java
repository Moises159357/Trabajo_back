package org.example.trabajo_back.servicesimplements;

import org.example.trabajo_back.entities.Citas;
import org.example.trabajo_back.repositories.ICitasRepository;
import org.example.trabajo_back.servicesinterfaces.ICitasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitasServiceImplement implements ICitasService {
    private final ICitasRepository cR;

    public CitasServiceImplement(ICitasRepository cR) {this.cR = cR;}


    @Override
    public void insertar(Citas c) {cR.save(c);}

    @Override
    public List<Citas> listar() {return cR.findAll();}

    @Override
    public Optional<Citas> listarId(Long id) {return cR.findById(id);}

    @Override
    public void actualizar(Citas c) {cR.save(c);}

    @Override
    public void eliminar(Long id) {cR.deleteById(id);}
}
