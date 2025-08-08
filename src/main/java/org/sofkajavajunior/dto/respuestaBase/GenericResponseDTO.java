package org.sofkajavajunior.dto.respuestaBase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponseDTO {
    @JsonProperty("procesoCorrecto")
    private boolean status;
    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("mensaje")
    private String message;

    public GenericResponseDTO(boolean status, Integer errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
