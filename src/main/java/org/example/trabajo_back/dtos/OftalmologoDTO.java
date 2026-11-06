package org.example.trabajo_back.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OftalmologoDTO {
    private Long idOftalmologo;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 50, message = "Los nombres no pueden superar los 50 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 50, message = "Los apellidos no pueden superar los 50 caracteres")
    private String apellidos;

    @NotBlank(message = "La colegiatura es obligatoria")
    @Size(max = 80, message = "La colegiatura no puede superar los 80 caracteres")
    private String colegiatura;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no puede superar los 100 caracteres")
    private String especialidad;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean disponibilidad;

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