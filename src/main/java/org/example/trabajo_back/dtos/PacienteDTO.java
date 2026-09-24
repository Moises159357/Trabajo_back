package org.example.trabajo_back.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class PacienteDTO {
    private Long idPaciente;
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    @NotBlank(message = "El Nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El Apellido es obligatorio")
    private String apellidos;
    @NotBlank(message = "El Genero es obligatorio")
    private String genero;
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    @NotBlank(message = "La Dirección es obligatoria")
    private String direccion;
    @NotBlank(message = "El Telefono es obligatorio")
    private String telefono;
    @NotNull(message = "La Fecha de Nacimineto es obligatoria")
    private Date fechaNacimiento;
    @NotNull(message = "La Fecha de Inscripción es obligatoria")
    private LocalDate fechaInscripcion;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
