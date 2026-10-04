package org.example.trabajo_back.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles")
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    // HistorialClinico y Receta aún no existen como entidades en el repo
    // (son de otros integrantes). Cuando se suban, cambiar estos dos campos a @ManyToOne.
    @Column(name = "idHistorial", nullable = false)
    private Long idHistorial;

    @Column(name = "idReceta", nullable = false)
    private Long idReceta;

    @ManyToOne
    @JoinColumn(name = "idTratamiento", nullable = false)
    private Tratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name = "idIntervencion", nullable = false)
    private Intervencion intervencion;

    @ManyToOne
    @JoinColumn(name = "idLente", nullable = false)
    private Lente lente;

    @Column(name = "observaciones", length = 800)
    private String observaciones;

    public Detalle() {
    }

    public Long getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Long idDetalle) { this.idDetalle = idDetalle; }

    public Long getIdHistorial() { return idHistorial; }
    public void setIdHistorial(Long idHistorial) { this.idHistorial = idHistorial; }

    public Long getIdReceta() { return idReceta; }
    public void setIdReceta(Long idReceta) { this.idReceta = idReceta; }

    public Tratamiento getTratamiento() { return tratamiento; }
    public void setTratamiento(Tratamiento tratamiento) { this.tratamiento = tratamiento; }

    public Intervencion getIntervencion() { return intervencion; }
    public void setIntervencion(Intervencion intervencion) { this.intervencion = intervencion; }

    public Lente getLente() { return lente; }
    public void setLente(Lente lente) { this.lente = lente; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
