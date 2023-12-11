package org.zerobase.publicwifiservice.exception;

import lombok.Getter;

@Getter
public class BookmarkGroupException extends RuntimeException {

    private ErrorCode errorCode;

    public BookmarkGroupException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BookmarkGroupException(ErrorCode errorCode, Exception causeException) {
        this.errorCode = errorCode;
        super.initCause(causeException);
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
