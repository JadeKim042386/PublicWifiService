package org.zerobase.publicwifiservice.exception;

import lombok.Getter;

@Getter
public class PublicWifiException extends RuntimeException {

    private ErrorCode errorCode;

    public PublicWifiException(ErrorCode errorCode, Exception causeException) {
        this.errorCode = errorCode;
        super.initCause(causeException);
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
