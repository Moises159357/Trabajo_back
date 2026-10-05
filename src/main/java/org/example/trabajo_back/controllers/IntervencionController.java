package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.IntervencionDTO;
import org.example.trabajo_back.dtos.TratamientoDTO;
import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.entities.Tratamiento;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IIntervencionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
}
