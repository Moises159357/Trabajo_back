package org.example.trabajo_back.controllers;

import jakarta.validation.Valid;
import org.example.trabajo_back.dtos.DetalleDTO;
import org.example.trabajo_back.dtos.ReporteLenteDTO;
import org.example.trabajo_back.entities.Detalle;
import org.example.trabajo_back.entities.Intervencion;
import org.example.trabajo_back.entities.Lente;
import org.example.trabajo_back.entities.Tratamiento;
import org.example.trabajo_back.exceptions.ResourceNotFoundException;
import org.example.trabajo_back.servicesinterfaces.IDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleController {
    private final IDetalleService dS;

    public DetalleController(IDetalleService dS) {
        this.dS = dS;
    }

    private DetalleDTO toDTO(Detalle d) {
        DetalleDTO dto = new DetalleDTO();
        dto.setIdDetalle(d.getIdDetalle());
        dto.setIdHistorial(d.getIdHistorial());
        dto.setIdReceta(d.getIdReceta());
        dto.setIdTratamiento(d.getTratamiento().getIdTratamiento());
        dto.setIdIntervencion(d.getIntervencion().getIdIntervencion());
        dto.setIdLente(d.getLente().getIdLente());
        dto.setObservaciones(d.getObservaciones());
        return dto;
    }

    // Las referencias se arman solo con el id; el service las valida contra la BD.
    private Detalle toEntity(DetalleDTO dto) {
        Detalle d = new Detalle();
        d.setIdDetalle(dto.getIdDetalle());
        d.setIdHistorial(dto.getIdHistorial());
        d.setIdReceta(dto.getIdReceta());
        d.setObservaciones(dto.getObservaciones());

        Tratamiento t = new Tratamiento();
        t.setIdTratamiento(dto.getIdTratamiento());
        d.setTratamiento(t);

        Intervencion i = new Intervencion();
        i.setIdIntervencion(dto.getIdIntervencion());
        d.setIntervencion(i);

        Lente l = new Lente();
        l.setIdLente(dto.getIdLente());
        d.setLente(l);
        return d;
    }

    @GetMapping("/listarDetalles")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<List<DetalleDTO>> listar() {
        return ResponseEntity.ok(dS.listar().stream().map(this::toDTO).toList());
    }

    @PostMapping("/registrarDetalle")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<DetalleDTO> registrar(@Valid @RequestBody DetalleDTO dto) {
        Detalle d = toEntity(dto);
        d.setIdDetalle(null); // siempre se crea uno nuevo
        dS.insertar(d);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/detalles/buscarDetallePorId/{id}")
                .buildAndExpand(d.getIdDetalle())
                .toUri();
        return ResponseEntity.created(location).body(toDTO(d));
    }

    @GetMapping("/buscarDetallePorId/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<DetalleDTO> buscarId(@PathVariable Long id) {
        Detalle d = dS.listarId(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe el detalle con el id: " + id));
        return ResponseEntity.ok(toDTO(d));
    }

    @PutMapping("/actualizarDetalle")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<DetalleDTO> actualizar(@Valid @RequestBody DetalleDTO dto) {
        if (dto.getIdDetalle() == null) {
            throw new ResourceNotFoundException("Debe indicar el idDetalle a actualizar");
        }
        dS.listarId(dto.getIdDetalle()).orElseThrow(() ->
                new ResourceNotFoundException("No existe el detalle con el id: " + dto.getIdDetalle()));
        Detalle d = toEntity(dto);
        dS.actualizar(d);
        return ResponseEntity.ok(toDTO(d));
    }

    @DeleteMapping("/eliminarDetalle/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        dS.listarId(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe el detalle con el id: " + id));
        dS.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Queries ----------

    @GetMapping("/buscarPorHistorial/{idHistorial}")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<List<DetalleDTO>> buscarPorHistorial(@PathVariable Long idHistorial) {
        return ResponseEntity.ok(dS.buscarPorHistorial(idHistorial).stream().map(this::toDTO).toList());
    }

    @GetMapping("/lentesMasUsados")
    @PreAuthorize("hasAnyRole('ADMIN','OFTALMOLOGO')")
    public ResponseEntity<List<ReporteLenteDTO>> lentesMasUsados() {
        return ResponseEntity.ok(dS.lentesMasUsados());
    }
}
