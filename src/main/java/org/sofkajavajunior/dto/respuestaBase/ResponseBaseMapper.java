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

    public static BaseResponseDTO generateOkResponseCreateUpdate(final Object nuevo) {
        return generateOkResponse(Arrays.asList(nuevo));
    }

    public static BaseResponseDTO generateOkResponseDelete(final Long oldId) {
        Map<String, Long> result = new HashMap<>();
        result.put("oldId", oldId);
        return generateOkResponse(Arrays.asList(result));
    }

    public static BaseResponseDTO generateErrorResponse(final String message) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setStatus(false);
        baseResponseDTO.setResult(null);
        baseResponseDTO.setErrorCode(1);
        baseResponseDTO.setMessage(message);
        return baseResponseDTO;
    }

}
