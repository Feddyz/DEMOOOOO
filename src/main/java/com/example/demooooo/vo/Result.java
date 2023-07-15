package com.example.demooooo.vo;
public class Result<T> {
    private Integer code;// 0 成功  1 失败

    private String message;// 失败后的提示信息

    private T data;

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(0, message, data);
    }

    public static <T> Result<T> ok(String message) {
        return new Result<>(0, message, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(1, message, null);
    }

    public static Result notLogin() {
        return new Result<>(2, "当前未登录", null);
    }

    public static Result notLogin(String message) {
        return new Result<>(2, message, null);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}