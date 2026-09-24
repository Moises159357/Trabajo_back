package org.example.trabajo_back.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "citas")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCitas;
    @Column(name = "fechaCita", nullable = false)
    private LocalDate fechaCita;
    @Column(name = "estado", length = 20, nullable = false)
    private String estado;
    @Column(name = "motivoCita", length =80, nullable = false)
    private String motivoCita;

    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;

    public Citas() {
    }

    public Citas(Long idCitas, LocalDate fechaCita, String estado, String motivoCita, Paciente paciente) {
        this.idCitas = idCitas;
        this.fechaCita = fechaCita;
        this.estado = estado;
        this.motivoCita = motivoCita;
        this.paciente = paciente;
    }

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
