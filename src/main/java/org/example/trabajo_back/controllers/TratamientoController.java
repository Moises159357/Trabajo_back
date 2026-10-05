package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.TratamientoDTO;
import org.example.trabajo_back.entities.Tratamiento;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.ITratamientoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {
    private final ITratamientoService tS;
    private final ModelMapper modelMapper;

    public TratamientoController(ITratamientoService tS, ModelMapper modelMapper) {
        this.tS = tS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<TratamientoDTO>> listar() {

        List<TratamientoDTO> lista = tS.list()
                .stream()
                .map(streaming -> modelMapper.map(streaming, TratamientoDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<TratamientoDTO> registrar(
            @Valid @RequestBody TratamientoDTO dto) {

        Tratamiento tr = modelMapper.map(dto, Tratamiento.class);

        tS.insert(tr);

        TratamientoDTO responseDTO =
                modelMapper.map(tr, TratamientoDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tr.getIdTratamiento())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TratamientoDTO> actualizar(
            @Valid @RequestBody TratamientoDTO dto) {
        Tratamiento existente = tS.listId(dto.getIdTratamiento())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un tratamiento con el id: " + dto.getIdTratamiento()
                        )
                );
        Tratamiento tratamiento = modelMapper.map(dto, Tratamiento.class);
        tratamiento.setIdTratamiento(existente.getIdTratamiento());
        tS.update(tratamiento);
        TratamientoDTO responseDTO =
                modelMapper.map(tratamiento, TratamientoDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buscarPorID/{id}")
    public ResponseEntity<TratamientoDTO> buscarId(@PathVariable Long id) {
        Tratamiento tr = tS.listId(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "No existe el tratamiento con el id: " + id
                ));
        TratamientoDTO dto = modelMapper.map(tr, TratamientoDTO.class);
        return ResponseEntity.ok(dto);
    }
}
