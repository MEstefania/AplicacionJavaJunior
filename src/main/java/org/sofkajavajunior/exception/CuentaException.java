package org.sofkajavajunior.exception;

public class CuentaException extends RuntimeException {
    public static final String NO_EXISTE_CUENTA=
            "No existe una cuenta para esos datos.";
    public static final String YA_EXISTE_CUENTA=
            "Ya existe una cuenta para esos datos.";
    public CuentaException(String message) {
        super(message);
    }
}
