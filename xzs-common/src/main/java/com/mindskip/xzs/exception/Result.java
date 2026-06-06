package com.mindskip.xzs.exception;

public class Result<T> {
    private int code;
    private String message;
    private T response;

    private Result() {}

    public static <T> Result<T> ok() {
        Result<T> r = new Result<>();
        r.code = 0;
        r.message = "success";
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.code = 0;
        r.message = "success";
        r.response = data;
        return r;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> r = new Result<>();
        r.code = code;
        r.message = message;
        return r;
    }

    public static <T> Result<T> fail(String message) {
        return fail(1, message);
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getResponse() { return response; }
    public void setResponse(T response) { this.response = response; }
}
