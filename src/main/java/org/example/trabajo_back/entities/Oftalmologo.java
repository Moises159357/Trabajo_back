package org.example.trabajo_back.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "oftalmologo")
public class Oftalmologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOftalmologo;

    @Column(name = "nombres", length = 50, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 50, nullable = false)
    private String apellidos;

    @Column(name = "colegiatura", length = 80, nullable = false)
    private String colegiatura;

    @Column(name = "especialidad", length = 100, nullable = false)
    private String especialidad;

    @Column(name = "correo", length = 100, nullable = false)
    private String correo;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @Column(name = "disponibilidad", nullable = false)
    private Boolean disponibilidad;

    public Oftalmologo() {
    }

    public Oftalmologo(Long idOftalmologo, String nombres, String apellidos, String colegiatura, String especialidad, String correo, String telefono, Boolean disponibilidad) {
        this.idOftalmologo = idOftalmologo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.colegiatura = colegiatura;
        this.especialidad = especialidad;
        this.correo = correo;
        this.telefono = telefono;
        this.disponibilidad = disponibilidad;
    }

    public Long getIdOftalmologo() { return idOftalmologo; }
    public void setIdOftalmologo(Long idOftalmologo) { this.idOftalmologo = idOftalmologo; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Boolean getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(Boolean disponibilidad) { this.disponibilidad = disponibilidad; }
}
