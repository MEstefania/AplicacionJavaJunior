package org.devsutest.exception;

public class CuentaException extends RuntimeException {
    public static final String NO_EXISTE_CUENTA=
            "No existe una cuenta para esos datos.";
    public static final String YA_EXISTE_CUENTA=
            "Ya existe una cuenta para esos datos.";
    public static final String NO_TIENE_CUENTA=
            "El cliente no tiene cuentas registradas..";
    public CuentaException(String message) {
        super(message);
    }
}
