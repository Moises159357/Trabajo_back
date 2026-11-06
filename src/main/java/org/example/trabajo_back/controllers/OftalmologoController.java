package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.OftalmologoDTO;
import org.example.trabajo_back.entities.Oftalmologo;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IOftalmologoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/oftalmologos")
public class OftalmologoController {
    private final IOftalmologoService oS;
    private final ModelMapper modelMapper;

    public OftalmologoController(IOftalmologoService oS, ModelMapper modelMapper) {
        this.oS = oS;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'PACIENTE')")
    public ResponseEntity<List<OftalmologoDTO>> listar() {
        List<OftalmologoDTO> lista = oS.list()
                .stream()
                .map(oftalmologo -> modelMapper.map(oftalmologo, OftalmologoDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OftalmologoDTO> registrar(@Valid @RequestBody OftalmologoDTO dto) {
        Oftalmologo o = modelMapper.map(dto, Oftalmologo.class);
        oS.insert(o);

        OftalmologoDTO responseDTO = modelMapper.map(o, OftalmologoDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(o.getIdOftalmologo())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/buscarID/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PACIENTE')")
    public ResponseEntity<OftalmologoDTO> buscarId(@PathVariable Long id) {
        Oftalmologo o = oS.listId(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe el oftalmólogo con el id: " + id));

        OftalmologoDTO dto = modelMapper.map(o, OftalmologoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OftalmologoDTO> actualizar(@Valid @RequestBody OftalmologoDTO dto) {
        Oftalmologo existente = oS.listId(dto.getIdOftalmologo())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe un oftalmólogo con el id: " + dto.getIdOftalmologo())
                );

        Oftalmologo oftalmologo = modelMapper.map(dto, Oftalmologo.class);
        oftalmologo.setIdOftalmologo(existente.getIdOftalmologo());
        oS.update(oftalmologo);

        OftalmologoDTO responseDTO = modelMapper.map(oftalmologo, OftalmologoDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Oftalmologo oftalmologo = oS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe un oftalmólogo con el id: " + id)
                );
        oS.delete(oftalmologo.getIdOftalmologo());
        return ResponseEntity.noContent().build();
    }
}