package org.sofkajavajunior.dto.respuestaBase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponseDTO {
    @JsonProperty("procesoCorrecto")
    private boolean status;
    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("mensaje")
    private String message;

    public GenericResponseDTO() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
