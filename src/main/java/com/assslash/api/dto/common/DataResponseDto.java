package com.assslash.api.dto.common;

import lombok.Getter;

@Getter
public class DataResponseDto<T> extends ResponseDto {
    private final T data;

    private DataResponseDto(T data) {
        super(RespCode.OK.getCode(), RespCode.OK.getMessage());
        this.data = data;
    }

    public static <T> DataResponseDto<T> from(T data) {
        return new DataResponseDto<>(data);
    }
}
