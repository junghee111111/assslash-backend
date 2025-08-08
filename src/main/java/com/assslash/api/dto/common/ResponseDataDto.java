package com.assslash.api.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDataDto<T> extends ResponseDto {
    private final T data;

    public ResponseDataDto(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseDataDto<T>> of(RespCode respCode, T data) {
        ResponseDataDto<T> dto = new ResponseDataDto<>(respCode.getCode(), respCode.getMessage(), data);
        return ResponseEntity
                .status(respCode.getCode())
                .body(dto);
    }


}
