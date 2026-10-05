package org.example.trabajo_back.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "lentes")
public class Lente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLente;

    @Column(name = "modelo", length = 50, nullable = false)
    private String modelo;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    public Lente() {
    }

    public Lente(Long idLente, String modelo, String marca, BigDecimal precio) {
        this.idLente = idLente;
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
    }

    public Long getIdLente() { return idLente; }
    public void setIdLente(Long idLente) { this.idLente = idLente; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
}
