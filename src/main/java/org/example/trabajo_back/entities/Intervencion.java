package org.example.trabajo_back.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "intervenciones")
public class Intervencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntervencion;

    @Column(name = "idVenta", nullable = false)
    private Long idVenta;

    @Column(name = "tipo",length = 50,nullable = false)
    private String tipo;

    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion",length = 1000,nullable = false)
    private String descripcion;

    @Column(name = "indicaciones",length = 1000,nullable = false)
    private String indicaciones;

    public Intervencion(Long idIntervencion, Long idVenta, String tipo, LocalDate fecha, String descripcion, String indicaciones) {
        this.idIntervencion = idIntervencion;
        this.idVenta = idVenta;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
    }

    public Intervencion() {
    }

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
