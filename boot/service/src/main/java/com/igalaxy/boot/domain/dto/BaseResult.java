package com.igalaxy.boot.domain.dto;

import com.google.gson.annotations.Expose;

/**
 * ajax 请求的返回类型封装JSON结果
 */
public class BaseResult<T> {

    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_SERVER_ERROR = 500;

    @Expose
    private int status;
    @Expose
    private int code;
    @Expose
    private String msg;

    private T data;

    public boolean isSuccess() {
        return status == STATUS_OK;
    }

    public static BaseResult ok() {
        return new BaseResult(STATUS_OK, 0, "成功", null);
    }


    public static <T> BaseResult<T> ok(T data) {
        return ok(0, data);
    }

    public static <T> BaseResult<T> ok(int code, T data) {
        return ok(code, "成功", data);
    }

    public static BaseResult ok(String msg) {
        return ok(0, msg);
    }


    public static BaseResult ok(int code, String msg) {
        return ok(code, msg, null);
    }

    public static <T> BaseResult<T> ok(int code, String msg, T data) {
        return create(STATUS_OK, code, msg, data);
    }

    public static BaseResult badRequest(String msg) {
        return badRequest(-1, msg);
    }

    public static BaseResult badRequest(int code, String msg) {
        return create(STATUS_BAD_REQUEST, code, msg, null);
    }

    public static BaseResult serviceError(String msg) {
        return serviceError(-1, msg);
    }

    public static BaseResult serviceError(int code, String msg) {
        return create(STATUS_SERVER_ERROR, code, msg, null);
    }

    public static <T> BaseResult<T> create(int status, int code, String msg, T data) {
        return new BaseResult(status, code, msg, data);
    }

    private BaseResult(int status, int code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
