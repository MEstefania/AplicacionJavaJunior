package org.sofkajavajunior.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sofkajavajunior.model.Cuenta;
import org.sofkajavajunior.model.Movimiento;

import java.math.BigDecimal;
import java.util.List;

public class MovimientoPorCuentaDTO {
    @JsonProperty("cuenta")
    private Cuenta cuenta;

    @JsonProperty("saldo")
    private BigDecimal saldo;

    @JsonProperty("movimientos")
    private List<MovimientoDTO> movimientos;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<MovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
