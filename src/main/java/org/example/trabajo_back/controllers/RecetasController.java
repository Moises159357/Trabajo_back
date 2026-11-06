package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.RecetasDTO;
import org.example.trabajo_back.entities.Recetas;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IRecetasService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetasController {
    private final IRecetasService rS;
    private final ModelMapper modelMapper;

    public RecetasController(IRecetasService rS, ModelMapper modelMapper) {
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<RecetasDTO>> listar() {

        List<RecetasDTO> lista = rS.list()
                .stream()
                .map(receta -> modelMapper.map(receta, RecetasDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registrar")
    public ResponseEntity<RecetasDTO> registrar(
            @Valid @RequestBody RecetasDTO dto) {

        Recetas tr = modelMapper.map(dto, Recetas.class);

        rS.insert(tr);

        RecetasDTO responseDTO =
                modelMapper.map(tr, RecetasDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tr.getIdReceta())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<RecetasDTO> actualizar(
            @Valid @RequestBody RecetasDTO dto) {
        Recetas existente =rS.listId(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la receta con el id: " + dto.getIdReceta()
                        )
                );
        Recetas receta = modelMapper.map(dto, Recetas.class);
        receta.setIdReceta(existente.getIdReceta());
        rS.update(receta);
        RecetasDTO responseDTO =
                modelMapper.map(receta, RecetasDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buscarPorID/{id}")
    public ResponseEntity<RecetasDTO> buscarId(@PathVariable Long id) {
        Recetas tr = rS.listId(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "No existe la receta con el id: " + id
                ));
        RecetasDTO dto = modelMapper.map(tr, RecetasDTO.class);
        return ResponseEntity.ok(dto);
    }
}
