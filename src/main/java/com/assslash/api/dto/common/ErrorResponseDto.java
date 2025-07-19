package com.assslash.api.dto.common;

import java.time.LocalDateTime;

public class ErrorResponseDto extends ResponseDto{
    private ErrorResponseDto(RespCode respCode) {
        super(respCode.getCode(), respCode.getMessage());
    }

    private ErrorResponseDto(RespCode respCode, Exception e) {
        super(respCode.getCode(), respCode.getMessage(e));
    }

    public static ErrorResponseDto from(RespCode respCode) {
        return new ErrorResponseDto(respCode);
    }

    public static ErrorResponseDto of(RespCode respCode, Exception e) {
        return new ErrorResponseDto(respCode,e);
    }
}
