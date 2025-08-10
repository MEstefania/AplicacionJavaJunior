package org.sofkajavajunior.exception;

public class ClienteException extends RuntimeException{
    public static final String NO_EXISTE_CLIENTE=
            "No existe un cliente para esos datos.";
    public static final String YA_EXISTE_CLIENTE=
            "Ya existe un cliente para esos datos.";
    public ClienteException(String message) {
        super(message);
    }
}
