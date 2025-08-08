package org.sofkajavajunior.dto.respuestaBase;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BaseResponseDTO extends GenericResponseDTO {
    @JsonProperty("retorno")
    private List<Object> result;

    public BaseResponseDTO() {
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }
}
