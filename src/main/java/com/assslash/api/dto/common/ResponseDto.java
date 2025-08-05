package com.assslash.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private final int code;
    private final String message;

    public static ResponseEntity<ResponseDto> of(RespCode respCode) {
        ResponseDto dto = new ResponseDto(respCode.getCode(), respCode.getMessage());
        return ResponseEntity
                .status(respCode.getCode())
                .body(dto);
    }

    public static ResponseDto of(RespCode respCode, String message) {
        return new ResponseDto(respCode.getCode(), message);
    }
}
