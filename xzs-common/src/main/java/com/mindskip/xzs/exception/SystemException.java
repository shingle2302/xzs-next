package com.mindskip.xzs.exception;

public class SystemException extends RuntimeException {
    private final int code;

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }
}
