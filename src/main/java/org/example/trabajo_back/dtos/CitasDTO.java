package org.example.trabajo_back.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CitasDTO {
    private Long idCitas;
    @NotNull(message = "Fecha de la cita obligatoria")
    private LocalDate fechaCita;
    @NotBlank(message = "Se necesita el Estado")
    private String estado;
    @NotBlank(message = "Se necesita el Motivo de la Cita")
    private String motivoCita;
    @NotNull(message = "El id del paciente es obligatorio")
    private Long idPaciente;

    public Long getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Long idCitas) {
        this.idCitas = idCitas;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
