package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.LenteDTO;
import org.example.trabajo_back.entities.Lente;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.ILenteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lentes")
public class LenteController {
    private final ILenteService lS;
    private final ModelMapper modelMapper;

    public LenteController(ILenteService lS, ModelMapper modelMapper) {
        this.lS = lS;
        this.modelMapper = modelMapper;
    }

    private LenteDTO toDTO(Lente l) {
        return modelMapper.map(l, LenteDTO.class);
    }

    @GetMapping("/listarLentes")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO','PACIENTE')")
    public ResponseEntity<List<LenteDTO>> listar() {
        return ResponseEntity.ok(lS.listar().stream().map(this::toDTO).toList());
    }

    @PostMapping("/registrarLente")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<LenteDTO> registrar(@Valid @RequestBody LenteDTO dto) {
        Lente l = modelMapper.map(dto, Lente.class);
        l.setIdLente(null); // siempre se crea uno nuevo
        lS.insertar(l);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/lentes/buscarLentePorId/{id}")
                .buildAndExpand(l.getIdLente())
                .toUri();
        return ResponseEntity.created(location).body(toDTO(l));
    }

    @GetMapping("/buscarLentePorId/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO','PACIENTE')")
    public ResponseEntity<LenteDTO> buscarId(@PathVariable Long id) {
        Lente l = lS.listarId(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe el lente con el id: " + id));
        return ResponseEntity.ok(toDTO(l));
    }

    @PutMapping("/actualizarLente")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<LenteDTO> actualizar(@Valid @RequestBody LenteDTO dto) {
        if (dto.getIdLente() == null) {
            throw new ResourceNotFoundException("Debe indicar el idLente a actualizar");
        }
        Lente existente = lS.listarId(dto.getIdLente()).orElseThrow(() ->
                new ResourceNotFoundException("No existe el lente con el id: " + dto.getIdLente()));
        Lente l = modelMapper.map(dto, Lente.class);
        l.setIdLente(existente.getIdLente());
        lS.actualizar(l);
        return ResponseEntity.ok(toDTO(l));
    }

    @DeleteMapping("/eliminarLente/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lS.listarId(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe el lente con el id: " + id));
        lS.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Queries ----------

    @GetMapping("/buscarPorMarca/{marca}")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO','PACIENTE')")
    public ResponseEntity<List<LenteDTO>> buscarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(lS.buscarPorMarca(marca).stream().map(this::toDTO).toList());
    }

    @GetMapping("/buscarPorPrecio")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO','PACIENTE')")
    public ResponseEntity<List<LenteDTO>> buscarPorPrecio(@RequestParam BigDecimal min,
                                                          @RequestParam BigDecimal max) {
        return ResponseEntity.ok(lS.buscarPorRangoPrecio(min, max).stream().map(this::toDTO).toList());
    }
}
