package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.CitasDTO;
import org.example.trabajo_back.entities.Citas;
import org.example.trabajo_back.entities.Paciente;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.ICitasService;
import org.example.trabajo_back.servicesinterfaces.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitasController {
    private final ICitasService cS;
    private final IPacienteService pS;
    private final ModelMapper modelMapper;

    public CitasController(ICitasService cS, IPacienteService pS, ModelMapper modelMapper) {
        this.cS = cS;
        this.pS = pS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CitasDTO>> listar(){
        List<CitasDTO> lista = cS.listar()
                .stream()
                .map(citas -> modelMapper.map(citas, CitasDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<CitasDTO> registrar(@Valid @RequestBody CitasDTO dto){
        Paciente p = pS.listId(dto.getIdPaciente())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe el paciente con el id: " + dto.getIdPaciente()
                        )
                );
        Citas ct = modelMapper.map(dto, Citas.class);
        ct.setIdCitas(ct.getIdCitas());
        cS.insertar(ct);

        CitasDTO responseDTO = modelMapper.map(ct, CitasDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ct.getIdCitas())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/{id{")
    public ResponseEntity<CitasDTO> buscarPorId(@PathVariable Long id){
        Citas ct = cS.listarId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la cita con el id: " + id
                        )
                );

        CitasDTO dto = modelMapper.map(ct, CitasDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<CitasDTO> actualizar (@Valid @RequestBody CitasDTO dto) {

        // 1. Verificar que la película exista
        Optional<Citas> existente = cS.listarId(dto.getIdCitas());

        if (existente.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No existe la cita con el id: " + dto.getIdCitas()
            );
        }

        // 2. Verificar que el streaming exista
        Optional<Paciente> paciente = pS.listId(dto.getIdPaciente());

        if (paciente.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No existe el paciente con el id: " + dto.getIdPaciente()
            );
        }

        // 3. Obtener la película existente
        Citas citas = existente.get();

        // 4. Actualizar sus campos
        citas.setFechaCita(dto.getFechaCita());
        citas.setEstado(dto.getEstado());
        citas.setMotivoCita(dto.getMotivoCita());

        // 5. Asignar el streaming existente
        citas.setPaciente(paciente.get());

        // 6. Guardar
        cS.actualizar(citas);

        // 7. Convertir a DTO
        CitasDTO responseDTO =
                modelMapper.map(citas, CitasDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Citas ct = cS.listarId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la cita con el id: " + id
                        )
                );
        cS.eliminar(ct.getIdCitas());
        return ResponseEntity.noContent().build();
    }
}
