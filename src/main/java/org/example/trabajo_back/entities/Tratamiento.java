package org.example.trabajo_back.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDate;

@Entity
@Table(name = "Tratamientos")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratamiento;

    @Column(name = "indicaciones",length = 1000,nullable = false)
    private String indicaciones;

    @Column(name = "duracionDias",nullable = false)
    private int duracionDias;

    @Column(name = "tipo",length = 50,nullable = false)
    private String tipo;

    @Column(name = "nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name = "estado",length = 30,nullable = false)
    private String estado;

    @Column(name = "fechaRegistro",nullable = false)
    private LocalDate fechaRegistro;

    public Tratamiento(Long idTratamiento, String indicaciones, int duracionDias, String tipo, String nombre, String estado, LocalDate fechaRegistro) {
        this.idTratamiento = idTratamiento;
        this.indicaciones = indicaciones;
        this.duracionDias = duracionDias;
        this.tipo = tipo;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Tratamiento() {
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
