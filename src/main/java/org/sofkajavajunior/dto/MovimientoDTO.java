package org.sofkajavajunior.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MovimientoDTO {
    @JsonProperty("id_movimiento")
    private Long idMovimiento;

    @JsonProperty("fecha")
    private Timestamp fecha;

    @NotNull(message = "El tipo no puede ser nulo.")
    @JsonProperty("tipo")
    private String tipo;

    @NotNull(message = "El valor no puede ser nulo.")
    @JsonProperty("valor_movimiento")
    private BigDecimal valorMovimiento;

    @JsonProperty("saldo")
    private BigDecimal saldo;

    @NotNull(message = "La cuenta no puede ser nulo.")
    @JsonProperty("id_cuenta")
    private Long idCuenta;

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
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
