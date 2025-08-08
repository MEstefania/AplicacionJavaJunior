package org.sofkajavajunior.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "cuenta",
        schema = "sofka")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cue", nullable = false)
    private Long id;
    @Column(name = "numero_cue", nullable = false)
    private String numeroCuenta;
    @Column(name = "tipo_cue",nullable = false, length = 15)
    private String tipo;
    @Column(name = "saldo_inicial_cue",nullable = false)
    private BigDecimal saldoInicial;
    @Column(name = "estado_cue",nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(
            name = "id_per",
            referencedColumnName = "id_per",
            foreignKey = @ForeignKey(name = "fk_cuenta_cliente_id"),
            nullable = false)
    private Cliente idCliente;

    public Cuenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
}
