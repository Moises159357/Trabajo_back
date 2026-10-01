package org.example.trabajo_back.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class IntervencionDTO {

    private Long idIntervencion;
    @NotNull(message = "El ID de venta es obligatorio")
    private Long idVenta;
    @NotBlank(message = "El tipo de intervencion es obligatorio")
    private String tipo;
    @NotNull(message = "La fecha de la intervencion es obligatorio")
    private LocalDate fecha;
    @NotBlank(message = "La descripcion de la intervencion es obligatorio")
    private String descripcion;
    @NotBlank(message = "Las indicaciones de la intervencion son obligatorias")
    private String indicaciones;

    public Long getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(Long idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
