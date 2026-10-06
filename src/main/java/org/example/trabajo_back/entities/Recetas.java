package org.example.trabajo_back.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Recetas")
public class Recetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @Column(name = "fechaInicio",nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin",nullable = false)
    private LocalDate fechaFin;

    @Column(name = "indicaciones",length = 1000, nullable = false)
    private String indicaciones;

    public Recetas() {
    }

    public Recetas(Long idReceta, LocalDate fechaInicio, LocalDate fechaFin, String indicaciones) {
        this.idReceta = idReceta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.indicaciones = indicaciones;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
