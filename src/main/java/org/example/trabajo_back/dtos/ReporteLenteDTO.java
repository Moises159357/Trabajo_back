package org.example.trabajo_back.dtos;

import java.math.BigDecimal;

public class ReporteLenteDTO {
    private Long idLente;
    private String marca;
    private String modelo;
    private BigDecimal precio;
    private Long totalDetalles;

    public ReporteLenteDTO() {
    }

    public ReporteLenteDTO(Long idLente, String marca, String modelo, BigDecimal precio, Long totalDetalles) {
        this.idLente = idLente;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.totalDetalles = totalDetalles;
    }

    public Long getIdLente() { return idLente; }
    public void setIdLente(Long idLente) { this.idLente = idLente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Long getTotalDetalles() { return totalDetalles; }
    public void setTotalDetalles(Long totalDetalles) { this.totalDetalles = totalDetalles; }
}
