package org.sofkajavajunior.dto.respuestaBase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResponseBaseMapper {

    public ResponseBaseMapper() {
    }

    public static BaseResponseDTO generateOkResponse(final List<Object> result) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setStatus(true);
        baseResponseDTO.setResult(result);
        baseResponseDTO.setErrorCode(0);
        baseResponseDTO.setMessage(null);
        return baseResponseDTO;
    }
    public static BaseResponseSimpleDTO generateOkSimpleResponse(final Object result) {
        BaseResponseSimpleDTO baseResponseDTO = new BaseResponseSimpleDTO();
        baseResponseDTO.setStatus(true);
        baseResponseDTO.setResult(result);
        baseResponseDTO.setErrorCode(0);
        baseResponseDTO.setMessage(null);
        return baseResponseDTO;
    }
    public static BaseResponseDTO generateOkResponseCreateUpdate(final Long newId) {
        Map<String, Long> result = new HashMap<>();
        result.put("newId", newId);
        return generateOkResponse(Arrays.asList(result));
    }

}
