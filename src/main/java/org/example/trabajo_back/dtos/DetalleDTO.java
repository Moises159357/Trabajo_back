package org.example.trabajo_back.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DetalleDTO {
    private Long idDetalle;

    @NotNull(message = "El idHistorial es obligatorio")
    private Long idHistorial;

    @NotNull(message = "El idReceta es obligatorio")
    private Long idReceta;

    @NotNull(message = "El idTratamiento es obligatorio")
    private Long idTratamiento;

    @NotNull(message = "El idIntervencion es obligatorio")
    private Long idIntervencion;

    @NotNull(message = "El idLente es obligatorio")
    private Long idLente;

    @Size(max = 800, message = "Las observaciones no pueden superar los 800 caracteres")
    private String observaciones;

    public Long getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Long idDetalle) { this.idDetalle = idDetalle; }

    public Long getIdHistorial() { return idHistorial; }
    public void setIdHistorial(Long idHistorial) { this.idHistorial = idHistorial; }

    public Long getIdReceta() { return idReceta; }
    public void setIdReceta(Long idReceta) { this.idReceta = idReceta; }

    public Long getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(Long idTratamiento) { this.idTratamiento = idTratamiento; }

    public Long getIdIntervencion() { return idIntervencion; }
    public void setIdIntervencion(Long idIntervencion) { this.idIntervencion = idIntervencion; }

    public Long getIdLente() { return idLente; }
    public void setIdLente(Long idLente) { this.idLente = idLente; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
