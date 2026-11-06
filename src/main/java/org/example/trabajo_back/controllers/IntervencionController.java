package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.IntervencionDTO;
import org.example.trabajo_back.dtos.TratamientoDTO;
import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.entities.Tratamiento;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IIntervencionService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/intervenciones")
public class IntervencionController {
    private final IIntervencionService iS;
    private final ModelMapper modelMapper;


    public IntervencionController(IIntervencionService iS, ModelMapper modelMapper) {
        this.iS = iS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<IntervencionDTO>> listar() {

        List<IntervencionDTO> lista = iS.list()
                .stream()
                .map(streaming -> modelMapper.map(streaming, IntervencionDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('OFTALMOLOGO')")
    public ResponseEntity<IntervencionDTO> registrar(
            @Valid @RequestBody IntervencionDTO dto) {

        Intervencion in = modelMapper.map(dto, Intervencion.class);

        iS.insert(in);

        IntervencionDTO responseDTO =
                modelMapper.map(in, IntervencionDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(in.getIdIntervencion())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('OFTALMOLOGO')")
    public ResponseEntity<IntervencionDTO> actualizar(
            @Valid @RequestBody IntervencionDTO dto) {
        Intervencion existente = iS.listId(dto.getIdIntervencion())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una intervencion con el id: " + dto.getIdIntervencion()
                        )
                );
        Intervencion intervencion = modelMapper.map(dto, Intervencion.class);
        intervencion.setIdIntervencion(existente.getIdIntervencion());
        iS.update(intervencion);
        IntervencionDTO responseDTO =
                modelMapper.map(intervencion, IntervencionDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buscarPorID/{id}")
    public ResponseEntity<IntervencionDTO> buscarId(@PathVariable Long id) {
        Intervencion in = iS.listId(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "No existe la intervencion con el id: " + id
                ));
        IntervencionDTO dto = modelMapper.map(in, IntervencionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/obtenerIntervencionPorFecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<List<IntervencionDTO>> obtenerIntervencionesPorFecha(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,

            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin) {

        List<IntervencionDTO> lista = iS
                .obtenerIntervencionesPorFecha(fechaInicio, fechaFin)
                .stream()
                .map(item -> {
                    IntervencionDTO dto = new IntervencionDTO();

                    dto.setIdIntervencion(((Number) item[0]).longValue());
                    dto.setIdVenta(((Number) item[1]).longValue());
                    dto.setTipo((String) item[2]);
                    dto.setFecha((LocalDate) item[3]);
                    dto.setDescripcion((String) item[4]);
                    dto.setIndicaciones((String) item[5]);

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/obtenerIntervencionPortipo/{tipo}")
    public ResponseEntity<List<IntervencionDTO>> obtenerIntervencionesPorTipo(
            @PathVariable String tipo) {

        List<IntervencionDTO> lista = iS.obtenerIntervencionesPorTipo(tipo)
                .stream()
                .map(item -> {
                    IntervencionDTO dto = new IntervencionDTO();

                    dto.setIdIntervencion(((Number) item[0]).longValue());
                    dto.setIdVenta(((Number) item[1]).longValue());
                    dto.setTipo((String) item[2]);
                    dto.setFecha((LocalDate) item[3]);
                    dto.setDescripcion((String) item[4]);
                    dto.setIndicaciones((String) item[5]);

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }


}
