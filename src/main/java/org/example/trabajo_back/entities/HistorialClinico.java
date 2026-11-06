package org.example.trabajo_back.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HistorialClinico")
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @Column(name = "fechaResultado",nullable = false)
    private LocalDate fechaResultado;

    @Column(name = "agudezaVista",nullable = false)
    private Long agudezaVista;

    @Column(name = "presionIntraocular",nullable = false)
    private Long presionIntraocular;

    @Column(name = "diagnostico",length = 500, nullable = false)
    private String diagnostico;

    @ManyToOne
    @JoinColumn(name = "idCitas",nullable = false)
    private Citas cita;

    public HistorialClinico() {
    }

    public HistorialClinico(Long idHistorial, LocalDate fechaResultado, Long agudezaVista, Long presionIntraocular, String diagnostico, Citas cita) {
        this.idHistorial = idHistorial;
        this.fechaResultado = fechaResultado;
        this.agudezaVista = agudezaVista;
        this.presionIntraocular = presionIntraocular;
        this.diagnostico = diagnostico;
        this.cita = cita;
    }

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public Long getAgudezaVista() {
        return agudezaVista;
    }

    public void setAgudezaVista(Long agudezaVista) {
        this.agudezaVista = agudezaVista;
    }

    public Long getPresionIntraocular() {
        return presionIntraocular;
    }

    public void setPresionIntraocular(Long presionIntraocular) {
        this.presionIntraocular = presionIntraocular;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
    }
}

