package org.sofkajavajunior.dto.respuestaBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sofkajavajunior.dto.respuestaBase.GenericResponseDTO;

import java.util.List;

public class BaseRespondeDTO extends GenericResponseDTO {
    @JsonProperty("retorno")
    private List<Object> result;

    public BaseRespondeDTO(boolean status, Integer errorCode, String message, List<Object> result) {
        super(status, errorCode, message);
        this.result = result;
    }
}
