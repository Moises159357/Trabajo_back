package org.example.trabajo_back.dtos;

public class ReporteCitasDTO {
    private Long idPaciente;
    private String nombre;
    private Long totalCitas;
    private String oftalmologoFrecuente;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTotalCitas() {
        return totalCitas;
    }

    public void setTotalCitas(Long totalCitas) {
        this.totalCitas = totalCitas;
    }

    public String getOftalmologoFrecuente() {
        return oftalmologoFrecuente;
    }

    public void setOftalmologoFrecuente(String oftalmologoFrecuente) {
        this.oftalmologoFrecuente = oftalmologoFrecuente;
    }
}
