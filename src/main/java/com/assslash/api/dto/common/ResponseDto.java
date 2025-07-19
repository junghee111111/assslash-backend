package com.assslash.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private final String code;
    private final String message;

    public static ResponseDto of(RespCode respCode) {
        return new ResponseDto(respCode.getCode(), respCode.getMessage());
    }

    public static ResponseDto of(RespCode respCode, String message) {
        return new ResponseDto(respCode.getCode(), message);
    }
}
