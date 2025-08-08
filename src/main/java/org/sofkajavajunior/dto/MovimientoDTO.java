package org.sofkajavajunior.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class MovimientoDTO {
    @NotNull(message = "La fecha no puede ser nulo.")
    @JsonProperty("fecha")
    private Timestamp fecha;
    @NotNull(message = "El tipo no puede ser nulo.")
    @JsonProperty("tipo")
    private String tipo;
    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @JsonProperty("valor_movimiento")
    private BigDecimal valorMovimiento;
    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @JsonProperty("saldo")
    private BigDecimal saldo;
    @JsonProperty("id_cuenta")
    private Long idCuenta;

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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }
}
