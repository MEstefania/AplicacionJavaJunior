package org.sofkajavajunior.exception;

public class MovimientoException extends RuntimeException {
    public static final String NO_EXISTE_MOVIMIENTO=
            "No existe un movimiento para esos datos.";
    public static final String SALDO_NO_DISPONIBLE =
            "Saldo no disponible.";
    public MovimientoException(String message) {
        super(message);
    }
}
