package org.devsutest.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(
        name = "movimiento",
        schema = "devsu")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov", nullable = false)
    private Long id;
    @Column(name = "fecha_mov", nullable = false)
    private Timestamp fecha;
    @Column(name = "tipo_mov",nullable = false)
    private String tipo;
    @Column(name = "valor_mov",nullable = false)
    private BigDecimal valorMovimiento;
    @Column(name = "saldo_mov",nullable = false)
    private BigDecimal saldoMovimiento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "id_cue",
            referencedColumnName = "id_cue",
            foreignKey = @ForeignKey(name = "FK_MOVIMIENTO_CUENTA_ID"),
            nullable = false)
    private Cuenta idCuenta;

    public Movimiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public BigDecimal getSaldoMovimiento() {
        return saldoMovimiento;
    }

    public void setSaldoMovimiento(BigDecimal saldoMovimiento) {
        this.saldoMovimiento = saldoMovimiento;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }
}
