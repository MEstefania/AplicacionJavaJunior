package org.sofkajavajunior.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CuentaDTO {
    @JsonProperty("id_cuenta")
    private String idCuenta;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    @JsonProperty("numero_cuenta")
    private String numeroCuenta;

    @NotNull(message = "El tipo no puede ser nulo.")
    @JsonProperty("tipo")
    private String tipo;

    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @JsonProperty("saldo_inicial")
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado no puede ser nulo.")
    @JsonProperty("estado")
    private Boolean estado;

    @NotNull(message = "El cliente no puede ser nulo.")
    @JsonProperty("id_cliente")
    private Long idCliente;

    @JsonProperty("cliente")
    private String cliente;

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
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

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
