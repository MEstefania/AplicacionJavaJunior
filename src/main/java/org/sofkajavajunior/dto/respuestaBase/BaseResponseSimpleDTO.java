package org.sofkajavajunior.dto.respuestaBase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponseSimpleDTO extends GenericResponseDTO{
    @JsonProperty("retorno")
    private Object result;

    public BaseResponseSimpleDTO() {
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
