package org.zerobase.publicwifiservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PUBLIC_WIFI_CANT_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "와이파이 정보 업데이트에 실패했습니다."),
    PUBLIC_WIFI_NOT_FOUND_NEAREST(HttpStatus.NOT_FOUND, "가까운 와이파이 정보를 찾을 수 없습니다."),
    PUBLIC_WIFI_NOT_FOUND(HttpStatus.NOT_FOUND, "와이파이 정보를 찾을 수 없습니다.")
    ;

    private HttpStatus status;
    private String message;
}
