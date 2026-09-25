package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.PacienteDTO;
import org.example.trabajo_back.entities.Paciente;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    private final IPacienteService pS;
    private final ModelMapper modelMapper;

    public PacienteController(IPacienteService pS, ModelMapper modelMapper) {
        this.pS = pS;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listarP")
    public ResponseEntity<List<PacienteDTO>> listar(){
        List<PacienteDTO> lista = pS.list()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrarP")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PacienteDTO> registrar(@Valid @RequestBody PacienteDTO dto ){
        Paciente pc = modelMapper.map(dto, Paciente.class);

        pS.insert(pc);

        PacienteDTO responseDTO = modelMapper.map(pc, PacienteDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pc.getIdPaciente())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/buscarIDP/{id}")
    public ResponseEntity<PacienteDTO> buscarId(@PathVariable Long id){
        Paciente p = pS.listId(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "No existe el streaming con el id: " + id
                ));

        PacienteDTO dto = modelMapper.map(p, PacienteDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/actualizarPaciente")
    public ResponseEntity<PacienteDTO> actualizar(@Valid @RequestBody PacienteDTO dto){
        Paciente existe = pS.listId(dto.getIdPaciente())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe un paciente con el id: " + dto.getIdPaciente()
                        )
                );
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        paciente.setIdPaciente(existe.getIdPaciente());
        pS.update(paciente);
        PacienteDTO responseDTO = modelMapper.map(paciente, PacienteDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/eliminarPaciente/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Paciente paciente = pS.listId(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe un paciente con el id: " + id
                        )
                );
        pS.delete(paciente.getIdPaciente());
        return ResponseEntity.noContent().build();
    }
}
